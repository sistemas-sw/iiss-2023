# Prácticas de Implementación 2023

Las prácticas consistirán en construir y realizar la implementación de un ejemplo de cada [tema](/temas). La implementación se realizará en un lenguaje a elegir por el estudiante.

Los ejemplos serán similares a los estudiados en teoría, u otros alternativos propuestos por cada estudiante. La elección del ejemplo de un tema no excluye utilizar elementos de otros temas, aunque se intentará centrarlo sobre todo en elementos de construcción propios del tema elegido.

## Procedimiento

1. Elegir un lenguaje de implementación de entre los siguientes:
   - [Scala](https://www.scala-lang.org/)
   - [Python](https://www.python.org/)
   - [TypeScript](https://www.typescriptlang.org/) y JavaScript
   - [C#](https://docs.microsoft.com/en-us/dotnet/csharp/)
   - [Ruby](https://www.ruby-lang.org/)
   - Cualquier otro lenguaje que tenga un mecanismo útil de implementación para el tema en cuestión y el ejemplo propuesto
2. Proponer un ejemplo a implementar, bien similares a los estudiados en teoría, bien propuestos por el estudiante. 
   - Colocad el ejemplo que implementéis en una carpeta con el nombre del lenguaje que hayáis elegido. Por ejemplo:
      - `csharp`
      - `lua`
      - `kotlin`
      - `python`
      - `typescript`
      - `ruby`
      - `scala`
      - `swift`
3. Implementar cada ejemplo empleando los mecanismos del lenguaje elegido.
   - Incluir una breve explicación escrita en [markdown](https://en.wikipedia.org/wiki/Markdown), que incluya instrucciones sobre cómo construir, compilar y ejecutar el ejemplo.
   - Incluir en el código un programa principal breve de demostración del ejercicio.
   - Ubicar los ficheros markdown y el código fuente del ejemplo en la carpeta con el nombre del tema situada bajo la carpeta `temas`.
   - Los nombres permitidos para las carpetas a situar bajo `temas/` son: 
     - `encapsulacion`,
     - `herencia`,
     - `delegacion`,
     - `inyeccion`,
     - `anotaciones`,
     - `aspectos`,
     - `undefined`,
     - `lambdas`,
     - `asincrono`,
     - `observables`.
4. Publicar los ejemplos en un repositorio
   - Hacer un fork de este repositorio y notificar al profesor por correo sobre el nuevo repo, para que pueda actuar como _watcher_.
   - Todos los ficheros deben estar realizados sobre carpetas cuyos nombres coincidan con los nombres de los [temas](#temas) elegidos.
   - Cada ejemplo en cada carpeta debe incluir un fichero `README.md` con las instrucciones en markdown, desde las que se enlace a:
     - el código implementado
     - las pruebas construidas
     - los ficheros de configuración propios de la herramienta de construcción utilizada (v.g. makefiles, pom.xml, etc.) que sen necesarios para compilar y probar el ejemplo.
   - El repositorio principal debe incluir un `README.md` que enlace a cada uno de los ejemplos/temas implementados
5. Hacer la entrega en el campus virtual, indicando la URL de vuestro repositorio
6. Hacer la presentación del ejercicio realizado, de alguna de estas dos formas:
    - Pedir fecha al profesor para presentar el ejemplo en clase, o bien...
    - Grabar un video explicativo de menos de 8 minutos y entregarlo a través del campus virtual.

## Temas

### Bloque I. Orientación a Objetos

#### 1. Encapsulación

Implementar el ejemplo final de [Recorrido de listas](http://dodero.github.io/iiss/iiss-oop-1/#implementacion-alternativa-lista-v04) (o un ejemplo equivalente) usando los mecanismos del lenguaje para definición de tipos de datos, ocultación de la implementación, aplicación del principio de acceso uniforme y especificación de la inmutabilidad del estado de un objeto. Por ejemplo:

- En Ruby, se podrían usar `:symbols`, `module`, `@variables` de instancia, `attr_accessor`, `attr_reader`, `attr_writer`, etc.
- En Scala, se podrían usar modificadores de visibilidad (`private`, `protected`), `var`, `val`, `type`, `abstract`, `sealed`, etc.
- En C#, se podrían usar modificadores de visibilidad y _properties_ `get`,`set` e `init`, etc.

#### 2. Herencia

Implementar el ejemplo de [Aventura](http://dodero.github.io/iiss/iiss-oop-1/#ejemplo-aventura-v01) (o un ejemplo equivalente) usando los mecanismos de herencia y polimorfismo de inclusión que proporcione el lenguaje. Por ejemplo:

- En Scala, podría usarse el mecanismo básico de herencia (`extends`), mixins (`traits`), `with`, etc.
- En C#, se podrían usar el mecanismo básico de herencia, `interface`, `base`, `override`, `virtual`, `new`, etc.

#### 3. Delegación

Implementar el ejemplo final de [Orquesta](http://dodero.github.io/iiss/iiss-oop-2/#implementacion-alternativa-orquesta-v06) (o un ejemplo equivalente) usando los mecanismos del lenguaje para la delegación/composición. Intentará evitarse el uso de _lambdas_, que están destinadas a otro bloque. Por ejemplo:

- En C# podrían usarse `delegates`.
- En Scala, podrían usarse _first-class functions_.
- En Ruby, podrían usarse bloques, `proc`, `module` y `delegate`.

### Bloque II. Dependencias y Calidad de Código

#### 4. Inyección

 Implementar el ejemplo de la identificación de [BankAccount](http://dodero.github.io/iiss/iiss-oop-3/#ejercicio-identificador-de-bankaccount-con-inyeccion-de-dependencias) (o un ejemplo equivalente) y que se puedan definir diversos atributos para comparar cuentas, usando los mecanismos del lenguage para inyectar como dependencia el criterio de comparación. Por ejemplo:

- En Java, puede usarse el framework [Guice](https://github.com/google/guice/wiki/) o [Weld CDI](http://weld.cdi-spec.org/)
- En Scala, puede usarse el framework [Play](https://www.playframework.com/) que implementa la inyección de dependencias [en tiempo de compilación](https://www.playframework.com/documentation/2.8.x/ScalaCompileTimeDependencyInjection).

#### 5. Anotaciones

Implementar el ejemplo de [BankAccount](http://dodero.github.io/iiss/iiss-oop-3/#ejercicio-identificador-de-bankaccount-con-inyeccion-de-dependencias) (o un ejemplo equivalente) con diversos atributos comparables, usando los  mecanismos de anotaciones que el lenguaje proporcione para inyectar como dependencia el comparador a usar. Por ejemplo:

- En Java, podrían definirse anotaciones a medida para inyección de dependencias en _runtime_, según el [JSR 330](http://javax-inject.github.io/javax-inject/). Ejemplo: [Creating a Custom Annotation in Java](http://baeldung.com/java-custom-annotation) de Baeldung
- En TypeScript, podrían usarse [_decorators_](https://www.typescriptlang.org/docs/handbook/decorators.html).
- En Scala, puede usarse el framework Play que implementa el [JSR 330](http://javax-inject.github.io/javax-inject/).

#### 6. Aspectos

Implementar usando aspectos el ejemplo del caso [Editor de Figuras](http://dodero.github.io/iiss/iiss-aop/#caso-5-editor-de-figuras) (o un ejemplo equivalente). Por ejemplo:

- En Java, usar intercepción de métodos con [Guice](https://github.com/google/guice/wiki/AOP)
- En Python, usar [Spring Python](https://docs.spring.io/spring-python/1.2.x/sphinx/html/aop.html#aspect-oriented-programming)

#### 7. Undefined

Implementar el ejemplo del caso "MobileTester" del apartado [Optionals](http://dodero.github.io/iiss/iiss-err/#optionals) (o un ejemplo equivalente) usando los mecanismos del lenguaje para resolver el problema de los valores _undefined_ y el mal uso de `null`. Por ejemplo:

- En C++, podría usarse [`std::optional`]((https://en.cppreference.com/w/cpp/utility/optional))
- En Scala, podrían usarse los mecanismos de la biblioteca del lenguaje como `Option`, `Some`, `None`, `Either`, `Left`, `Right`, etc.

### Bloque III. Programación Funcional

#### 8. Lambdas

Implementar ejemplos del uso de funciones anónimas (expresiones _lambda_) empleando los mecanismos disponibles en el lenguaje elegido. Por ejemplo:

- En C++, usar lambdas genéricas de C++11, lambdas variádicas de C++14/C++17 y lambdas mutables.
- En Scala, usar [funciones anónimas](https://docs.scala-lang.org/scala3/book/fun-anonymous-functions.html) de Scala 3
- En Ruby, usar `lambdas`.

#### 9. Programación asíncrona

Implementar un ejemplo de programación asíncrona, similar a los mostrados en [Promesas y futuros](http://dodero.github.io/iiss/iiss-evp-2/#promesas), que emplee los mecanismos disponibles en el lenguaje. Por ejemplo:

- En C++, usar [`std::async`](https://en.cppreference.com/w/cpp/thread/async) y [`std::promise`](https://en.cppreference.com/w/cpp/thread/promise) y [`std::future`](https://en.cppreference.com/w/cpp/thread/future)
- En Scala, usar [`Future`](https://docs.scala-lang.org/overviews/core/futures.html) o `Promise` de `scala.concurrent`.

#### 10. Observables

Implementar un ejemplo de procesamiento de streams de eventos asíncronos empleando una biblioteca [ReactiveX](http://reactivex.io/intro.html) para el lenguaje elegido. Por ejemplo:

- En C++, usar los elementos _observable_ de la biblioteca [RxCpp](http://reactivex.io/RxCpp/index.html).
- En Scala, usar el framework [Akka](https://akka.io/) o el trait [`Observable`](https://reactivex.io/rxscala/scaladoc/index.html#rx.lang.scala.Observable) de las extensiones [RxScala](https://reactivex.io/rxscala/).
