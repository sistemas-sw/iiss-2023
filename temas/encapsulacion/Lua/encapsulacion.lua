--@author Jonathan Muñoz Morales

--@version 20/03/2023

--Ejemplo de encapsulación de Lua

--para compilar este código pegue en la terminal el siguiente comando estando situado en el directorio del fichero --> lua encapsulacion.lua


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
	end
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

--comenzamos con el main

s = Account:new{cantidad = 0.00}--damos de alta una cuenta corriente con una cantidad inicial de 0.00$

s:deposit(100.00)--depositamos 100.00$

s:withdraw(50.00)--retiramos 50.00$

print(s.cantidad)--nos dará la cantidad de 50$