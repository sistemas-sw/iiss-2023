# ENCAPSULACIÓN CON LUA
## Clases
Una clase trabaja como un molde para la creación de objetos. Muchos lenguajes orientados a objetos ofrecen este concepto de clase. En estos lenguajes, cada objeto es una instancia de una clase específica. Lua no ofrece este concepto de clase, cada objeto se define su propio comportamiento y tiene una forma propia. Sin embargo, no es complicado emular clases en Lua, siguiendo el ejemplo de lenguajes de programación como Self y NewtonScript. En estos lenguajes los objetos no tienen clases. En Lua, la forma de representar las clases es con tablas y metatablas.

## Tablas
La idea es implementar prototipos. Mas específicamente, si tenemos dos objetos **a** y **b**, todo lo que tenemos que hacer es esto:
```Lua
	setmetatable(a, {__index = b})
```
Después de la anterior línea de código, **a** buscará en **b** cualquier operación que no tenga. Para ver **b** como la clase de objeto de **a** no es más que un cambio en la terminología. Vayamos a un ejemplo un poco mas elavorado, veamos un ejemplo de un banco. Supongamos que queremos crear una clase banco en Lua, para ello reduzcamos el ejemplo en tan solo una variable para la cantidad de dinero que hay en el banco. Además, queremos encapsular dos funciones, una para ingresar y otra para retirar dinero. Por lo que en Lua quedaría:
```Lua
	local Account = {}
	
	function Account:new(o)
		o = o or {}
		setmetatable(o, self)
		self.__index = self
		return o
	end

	function Account:deposit(c)
		self.cantidad = self.cantidad + c
	end

	function Account:withdraw(c)
		if c > self.cantidad then
			error("saldo insuficiente")
		end
			self.cantidad = self.cantidad - c
	end
```

Bien, la función *new* se encargará de crear una nueva cuenta bancaria inicializando el saldo con la cantidad pasada como parámetro junto con el titular de dicha cuenta. Por otra parte, la función *deposit* suma la cantidad que se le pasa por valor a la cantidad actual de la cuenta y por último, *withdraw* resta la cantidad de saldo que se le pasa por valor, en caso de ser mayor que la cantidad actual muestra un mensaje de error.

Para poder encapsular los datos de la cuenta debemos definir una metatabla para la tabla/clase *Account* que controle los atributos de la tabla/clase.

## Metatablas

Cada metatabla tiene dos claves *__index* y *__newindex*, *__index*, define una función que se llama cuando se intenta acceder a un campo inexistente de la tabla *Account*. Por el contrario, *__newindex* define una función que se llama cuando se intenta asignar un valor a un campo inexistente de la tabla *Account*. Por lo que en código quedaría de la siguiente forma:

```Lua
local Account_mt = {
	__index = function(t, k)
		if k == "cantidad" then
			return t._o
		elseif k == "deposit" then
			return function(self, c)
				self:_deposit(c)
			end
		elseif k == "withdraw" then
			return function(self, c)
				self:_withdraw(c)
			end
		else
			error("intentando acceder a un elemento no declarado_ 0" .. k)
		end
	end,
	__newindex = function(t, k, v)
		error("intentando acceder a un elemento no delarado: " .. k)
	end,
}

function Account:_deposit(c)
	self.cantidad = self._cantidad + c
end

function Account:_withdraw(c)
	if c > self._cantidad then
		error("saldo insuficiente")
	end
	self._cantidad = self._cantidad - c
end

setmetatable(Account, Account_mt)
```

Lamentablemente, en Lua no existe la forma de declarar un método ni un objeto de forma privada, es decir, el concepto de atributos públicos o privados no existe, ya que Lua apunta a un camino de programas pequeños/medianos. Por otra parte, en Lua podemos usar las tablas como forma de aproximarnos a la privacidad de los atributos, de forma que podríamos dividir en tablas los atributos que queremos que solo sean modificables en una única tabla y otra tabla con todos los atributos que que queremos que sean solo observables.

## Compilar código Lua
Para compilar código Lua, debemos instalar el compilador de éste, por ello en ubuntu debemos escribir el siguiente comando en la terminal:
```
	sudo apt install lua5.3
```
En caso de que tengas un SO en el que no puedas ejecutar este comando le recomiendo que visite el siguiente enlace: https://www.solvetic.com/tutoriales/article/5802-como-instalar-lua-en-linux-o-windows-10/
