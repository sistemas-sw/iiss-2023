from abc import ABC, abstractmethod

class Instrumento(ABC):

    @abstractmethod
    def tocar():
        pass

    @abstractmethod
    def afinar():
        pass


class Viento(Instrumento):

    def tocar(self):
        self.soplar()
    
    def afinar(self):
        print("Afinar soplido.")

    def soplar(self):
        print("Soplar.")


class Cuerda(Instrumento):

    def tocar(self):
        self.rasgar()
    
    def afinar(self):
        print("Afinar rasgado.")

    def rasgar(self):
        print("Rasgar.")


class Percusion(Instrumento):

    def tocar(self):
        self.golpear()
    
    def afinar(self):
        print("Afinar golpeado.")

    def golpear(self):
        print("Golpear.")
