trait SabeLuchar{
	def luchar();
}
trait SabeNadar{
	def nadar();
}
trait SabeVolar{
	def volar();
}

class PersonajeDeAccion(){
    def luchar(): Unit = println("El Personaje de Accion lucha.")
}
class Heroe () extends PersonajeDeAccion with SabeLuchar with SabeNadar with SabeVolar{
    def nadar(): Unit = println("El heroe nada.")
    def volar(): Unit = println("El heroe vuela.")
}
object Aventura{ //Mirar si no es class
	def t(x: SabeLuchar): Unit = x.luchar()
	def u(x: SabeNadar): Unit = x.nadar()
	def v(x: SabeVolar): Unit = x.volar()
	def w(x:PersonajeDeAccion): Unit = x.luchar()

	def main(args: Array[String]) ={
		val i = new Heroe() //variable de tipo heroe
		t(i) //Tratar como un sabeLuchar
		u(i) //Tratar como un sabeNadar
		v(i) //Tratar como un sabeVolar
		w(i) //Tratar como un PersonajeDeAccion
	}

}

