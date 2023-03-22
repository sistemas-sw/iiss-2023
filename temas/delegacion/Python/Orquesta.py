from Instrumentos import *

class IteradorOrquesta():

    #El iterador necesita de los instrumentos (lista) sobre el que iterar
    def __init__(self, instrumentos):
        self.ins = instrumentos
        self.indiceActual = 0
        self.size = len(self.ins)

    def __iter__(self):
        return self
    
    def __next__(self):
        
        if self.indiceActual < self.size:
            instrumento = self.ins[self.indiceActual]
            self.indiceActual +=1
            return instrumento
    
        raise StopIteration


class Orquesta():
    def __init__(self):
        #Lista dinámica
        self.__instrumentos = []

    def addInstrumento(self, ins):
        self.__instrumentos.append(ins)

    def removeInstrumento(self, ins):
        self.__instrumentos.remove(ins)

    def tocar(self):
        for i in self.__instrumentos:
            i.tocar()

    def afinar(self,ins):
        ins.afinar()
        ins.tocar()

    #Delegamos el trabajo de iteracion
    def __iter__(self):
        return IteradorOrquesta(self.__instrumentos)