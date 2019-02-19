import numpy as np
import numpy.linalg as lin
import pandas as pd
from sklearn.preprocessing import StandardScaler


class mypca(object):
    '''
    k : component 수
    n : 원래 차원
    components : 고유벡터 저장소 shape (k,n)
    explain_values : 고유값 shape (k,)
    '''
    k = None
    components = None
    explain_values = None
    matrix_co = None

    def __init__(self, k=None, X_train=None):
        '''
        k의 값이 initial에 없으면 None으로 유지
        '''
        if k is not None :
            self.k = k       
        if X_train is not None:
            self.fit(X_train)


    def fit(self,X_train=None):
        if X_train is None:
            print('Input is nothing!')
            return
        if self.k is None:
            self.k = min(X_train.shape[0],X_train.shape[1])
            
        #############################################
        # TO DO                                     #
        # 인풋 데이터의 공분산행렬을 이용해         #
        # components와 explain_values 완성          # 
        #############################################
        #covariance

        #공분산 행렬, 차원*차원
        # print('X_train',np.shape(X_train))
        X_train=pd.DataFrame(X_train)
        self.matrix_co=X_train.cov()
        # print(self.matrix_co)
        self.matrix_co=np.array(self.matrix_co)
        # print('matrix',np.shape(self.matrix_co))
        #
        #고유값 shape (k,)
        self.explain_values=lin.eig(self.matrix_co)[0]
        #교유값의 크기 순서대로 고유벡터 나열 (2차원으로 축소하니 2번째 까지의 큰 값을 인덱스로 가져옴
        #원래는 매개변수 K 에맞게 일반화 구현해야 하나....하지못했음..

        first=self.explain_values.argsort()[::-1][0]
        second=self.explain_values.argsort()[::-1][1]
        #그 값에 해당하는 고유벡터

        self.components=lin.eig(self.matrix_co)[1][:,[first,second]]
        # print(np.shape(self.components))
        self.components=self.components.T

        #############################################
        # END CODE                                  #
        #############################################
        
        return
    
    def transform(self,X=None):
        if X is None:
            print('Input is nothing!')
            return
        
        result = None
        '''
        N : X의 행 수
        result의 shape : (N, k)
        '''
        #############################################
        # TO DO                                     #
        # components를 이용해 변환결과인            #
        # result 계산                               #
        #############################################

        #고유벡터
        # print(np.shape(X))
        #해당 고유벡터와 X의 내적!
        result = np.dot(self.components,X.T)
        # print(np.shape(result))
        
        #############################################
        # END CODE                                  #
        #############################################       
        return result.T
    
    def fit_transform(self,X=None):
        if X is None:
            print('Input is nothing!')
            return
        self.fit(X)
        return self.transform(X)