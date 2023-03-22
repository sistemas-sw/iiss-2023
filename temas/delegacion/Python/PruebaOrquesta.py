from Orquesta import *

if __name__ == "__main__":

    orquesta = Orquesta()
    orquesta.addInstrumento(Viento())
    orquesta.addInstrumento(Cuerda())
    orquesta.addInstrumento(Percusion())

    for i in orquesta:
        orquesta.afinar(i)

    print("")
    orquesta.tocar()