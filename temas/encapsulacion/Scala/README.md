## Compilación
Una vez tenemos instalado el compilador de scala:
>scalac PruebasLista.scala

## Ejecución
>scala PureubasLista

## Explicación
### Conceptos previos
#### Companion Objects
Scala, al igual que java, es un lenguaje de programación orientado a objetos pero éste no sólo cuenta con las clases sino que también tiene un añadido llamado "companion objects".

>Los **Companion Objects** se definen mediante la palabra reservada *object* y suelen ser *acompañantes* de una clase con el mismo nombre pero, a diferencia de las clases, éstos no cuentan con parámetros de entrada en su constructor, a su vez, pueden extender clases y traits así como ser miembros de clases o traits.

Éstos objetos acompañantes tienen múltiples usos, de los cuáles ahora nos va a ser útil uno en especial, para agrupar miembros static.

> Scala no cuenta con la palabra reservada *static*, en cambio, los miembros de un companion object son tratados como tal.

#### Scala traits
Este concepto se ha tratado en clase, la siguiente explicación está sacada de las diapositivas:
>Un trait es una forma de separar las dos principales responsabilidades de una clase:
definir el estado de sus instancias y definir su comportamiento
>- Las clases y los objetos en Scala pueden extender un trait
>- Los trait de Scala son similares a las interface de Java.
>- Los trait no pueden instanciarse
>- Los métodos definidos en una clase tienen precedencia sobre los de un trait
>- Los trait no tienen estado propio, sino el del objeto o la instancia de la clase a la
que se aplica

Para poder utilizarlos en las clases tenemos que extender en las clases dicho trait pues en scala no podemos implementar directamente.

#### Plantillas
En Scala las plantillas se implementan mediante el operador corchete en la declaración de la clase y no tienen ningún funcionamiento especial que vayamos a utilizar en este ejercicio.

### Implementación
En este caso tenemos los traits Liata e Iterator como traits, de igual manera que en el ejemplo de java a seguir, puesto que no hay interface de manera mas directa. Tras ello he implementado unas clases que hagan uso de estos traits.

Lo primero a comentar de la clase MiLista son las principales diferencias, o añadidos, con respecto al trait que extiende.

> La variable ArrayBuffer la utilizamos para almacenar los elementos de la lista, se podría hacer con algún tipo de más bajo nivel pero para el ejemplo en cuestion nos beneficia sus métodos ya implementados, los cuales usamos en la mayoría de los métodos, ya sean modificadores o consultores.
>
> Puesto a que estamos haciendo uso de *extend* necesitamos utilizar la palabra reservada *override* para sobreescribirlos.
>
> Los métodos *copy* y *equals* vienen a implementar el método *clone* e *isEqualto* de java mediante la forma que más se adecua a como se plantea en Scala.
>
>El método toString de la clase *MiLista* lo sobreescribimos para poder hacer uso de *println* en las pruebas.

La clase MyIterator no tiene especial complejidad:
>La variable *currentIndex* nos permite almacenar el indice actual en el recorrido de la lista.
>
> El método *get* ha sido añadido, de nuevo, para facilitar las pruebas.
>
>En el método *remove* nos es de especial utilidad que la estructura de datos en la que almacenamos la lista sea un ArrayBuffer pues nos permite conservar los cambios hechos en distintas clases cosa que, por ejemplo, con vector no podríamos.

En la implementación se ha buscado el evitar todo uso de elementos y prácticas deprecated, como el llamar a métodos definidos sin parametros sin el uso de un paréntesis vacío.