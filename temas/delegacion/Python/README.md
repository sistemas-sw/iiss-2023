## Compilación y ejecución
> python PruebaOrquesta.py

## Explicación

### Clases y herencia
Las clases en python pueden heredar poniendo entre paréntesis el nombre de la clase de la que hereda. El constructor es el método *\_\_init__*. Los métodos de las clases reciben como primer parámetro *self*.
> *self* se utiliza para poder acceder a los atributos y métodos de la propia clase así como referirse a si mismo como *this* en otros lenguajes.

En el siguiente ejemplo, en la clase hija heredamos de base, definimos un atributo y lo imprimimos en un método.
```python
    class base()
        def metodo1():
            pass
    class hija(base):
        def __init__(self):
            self.atributo1 = 0
        def metodo2(self):
            print(self.atributo1)
```
### Clases abstractas
Para poder utilizar las clases abstractas en python necesitamos importar el módulo *ABC* y designar los métodos abstractos que querámos cómo en el siguiente ejemplo:
```python
    from abc import ABC, abstractmethod

    class ClaseAbstracta(ABC):

        @abstractmethod
        def metodoAbstracto():
            pass
```

### Variables privadas
En python no existen variables privadas como en otros lenguajes, gracias a una práctica que ha sido muy utilizada, ahora las variables cuyo nombre empiece por una doble barra baja serán tratadas como privadas. Dicho sea, esta privacidad no es como la de Java, por poner un ejemplo, pues con persistencia se puede llegar a sortear.

### Iteradores
Para poder hacer a una clase iterable en python necesitamos delegar el trabajo de la iteración a una nueva clase que creemos. Ésta clase debe cumplir unas condiciones:
>- Débe tener un método *\_\_iter__*, en este caso tendrá un simple return self.
>- Tendrá un método *\_\_next__* en el que definirémos cuál será el siguiente elemento en la iteración y deberá lanzar *StopIteration* una vez que finalice el recorrido.

Para IteradorOrquesta he definido un constructor que inicialice el índice, el tamaño y reciba la lista de instrumentos a iterar. La clase que va a delegar el trabajo de iteración, y por tanto convertirse en iterable, tiene que tener un método *\_\_iter__* que devuelva una instancia de la clase iteradora. 
