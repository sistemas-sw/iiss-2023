import scala.collection.mutable.ArrayBuffer

trait Lista[T] {

    def addFirst(value: T): Unit
    def removeFirst(): Unit
    def addLast(value: T): Unit
    def removeLast(): Unit
    def first(): T
    def last(): T
    def isEmpty(): Boolean
    def length(): Int
    def copy(): Any

    //Al crear una clase que hereda de la interfaz no puedo poner como parámetro otro tipo,
    //Pues estaríamos comparando tipos distintos
    //def isEqualTo(other: Lista[T]): Boolean

    //Lo hacemos al estilo de Scala dónde el método se llama equals y recibe cualquier tipo
    def equals(arg0: Any): Boolean

    def iterator(): Iterator[T]
  }

trait Iterator [E] {
    def hasNext(): Boolean
    def next(): E
    def remove(): Unit
}


  class MiLista[T] extends Lista[T]{
    //variables
    protected var l = ArrayBuffer[T]();

    //Métodos
    override def addFirst(value: T): Unit = {l.prepend(value)}
    override def removeFirst(): Unit = {l.remove(0)}
    override def addLast(value: T): Unit = {l += value}
    override def removeLast(): Unit = {l.dropRightInPlace(1)}
    override def first(): T = {l(0)}
    override def last(): T = {l(l.length-1)}
    override def isEmpty(): Boolean = {l.isEmpty}
    override def length(): Int = {l.length}
    override def copy(): MiLista[T] = this;//new MiLista[E]();

    override def equals(arg0: Any): Boolean = {
      //Utilizamos la comparación del tipo ArrayBuffer, si no utilizaramos esta
      //tendíamos que hacer un match para ver primero cuál es el tipo que estamos recibiendo
      arg0 == l;
    }

    //Si ponemos el tipo de devolucion sea Iterator[T] entonces no podríamos utilizar más
    //Métodos que los que vienen declarados en el trait.
    override def iterator(): MyIterator[T] = new MyIterator[T](l)

    //Método de conversión a cadena
    override def toString() = {l.toString()}
  }

class MyIterator[E](var elements: ArrayBuffer[E]) extends Iterator[E] {
      private var currentIndex = 0

      override def hasNext(): Boolean = {currentIndex < elements.size}

      override def next(): E = {
          var element = elements(currentIndex)
          if(hasNext()){
              currentIndex += 1
              element = elements(currentIndex)
          }
          element
      }

      //Método añadido para facilitar las pruebas
      def get(): E = {elements(currentIndex);}
      
      override def remove(): Unit = {
          
          elements.remove(currentIndex);

          //Siempre que se pueda el indice va a ser el elemento anterior
          if (currentIndex > 0) {
            currentIndex -= 1
          }else if(currentIndex == 0 && hasNext())
            currentIndex +=1
      }
}


object PruebasLista {
        def main(args: Array[String]) ={
            println("Creamos la lista de Int...")
            var l1: MiLista[Int] = new MiLista[Int]();
            println("Añadimos el numero 1 y mostramos...")
            l1.addFirst(1);
            println(l1);

            println("\nCopiamos la anterior a una segunda lista de Int...")
            var l2: MiLista[Int] = l1.copy();
            println("Comparamos las dos y mostramos el resultado...")
            var comparition = l1.equals(l2);
            println(comparition);

            println("Seguimos metiendo en la primera lista por el principio y final...")
            l1.addLast(2);
            l1.addLast(3);
            l1.addFirst(0);
            println(l1);
            println("Eliminamos ahora el ultimo elemento")
            l1.removeLast();
            println(l1);

            println("\nPasamos a comprobar el uso de iteradores...")
            var it = l1.iterator();
            println("Mostramos que es el primer elemento y comprobamos si tiene siguiente...")
            println(it.get());
            println(it.hasNext())
            println("Vamos al siguiente y lo mostramos")
            it.next()
            println(it.get());
            println("Eliminamos el elemento actual y mostramos en cuál nos quedamos")
            it.remove()
            println(it.get())
            println("Vamos al siguiente y lo mostramos...")
            it.next()
            println(it.get())
            println("Y mostramos el estado actual de la lista...")
            println(l1)
        }
}

  