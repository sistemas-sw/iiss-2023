## Compilación
Una vez tenemos instalado el compilador de scala:
>scalac AventuraTest.scala

## Ejecución
>scala AventuraTest

## Explicación
He implementado el ejemplo Aventura 2.0, lo primero, unos conceptos previos.
### Conceptos previos
Scala, al igual que java, es un lenguaje de programación orientado a objetos pero éste no sólo cuenta con las clases sino que también tiene un añadido llamado "companion objects".

>Los **Companion Objects** se definen mediante la palabra reservada *object* y suelen ser *acompañantes* de una clase con el mismo nombre pero, a diferencia de las clases, éstos no cuentan con parámetros de entrada en su constructor, a su vez, pueden extender clases y traits así como ser miembros de clases o traits.

Éstos objetos acompañantes tienen múltiples usos, de los cuáles ahora nos va a ser útil uno en especial, para agrupar miembros static.

> Scala no cuenta con la palabra reservada *static*, en cambio, los miembros de un companion object son tratados como tal.

Como ejemplo de signatura aquí unos ejemplos de clases y objetos en los que podemos ver que los object no tienen porque tener una clase acompañante:

```scala
    class example(){
        var num = 0
        def hello(): Unit = println("Hello world")
    }

    object example2(){
        var num = 1
        def multiplication_x2(n: Int): Int = 2*n
    }
```

Los traits en Scala tienen un comportamiento parecido a las interface en Java.

```scala
    trait example{
	    def hello();
    }
```

### Herencia
La herencia en scala se comporta de manera parecida a la de java.
>Contamos con la palabra reservada **extend** para extender una clase como mucho. En cambio, podemos implementar múltiples clases usando **with**.

```scala
    trait interface{
        def hello()
    }

    //No necesita override
    class base(){
        def hello(): Unit = println("hello world")
    }
    class example () extends PersonajeDeAccion with interface{}
```

Con todo esto tenemos ya todas las herramientas para implementar el ejemplo Aventura 2.0, los métodos static se encuentran en el object aventura, no sobrescribimos ningún método por lo que no necesitamos usar override y el ejemplo lo tenemos en main dónde se trata el objeto Héroe de distintas maneras.

### Implementación
Tenemos dos versiones, Aventura1 y AventuraTest. En la primera podemos observar, que tal como aparece en el ejemplo a seguir, luego he decidido implementar una pequeña mejora en AventuraTest, sacando el main, que se usa para pruebas, en una clase distinta destinada únicamente para estas pruebas.

Pasando a comentar la implementación más propiamente, en AventuraTest, las interface del programa de java han sido implementadas como traits en scala así como heredamos una clase con extend y los implements con with.

Tenemos los Object Aventura y AventuraTest, Aventura no se implementa como clase debido a que sus métodos en el ejemplo son static, lo mismo con AventuraTest ya que el main debe ser static.
