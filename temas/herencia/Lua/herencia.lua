--@author Jonathan Muñoz Morales

--@version 20/03/2023

--Ejemplo de herencia de Lua

--para compilar este código pegue en la terminal el siguiente comando estando situado en el directorio del fichero --> lua herencia.lua


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


--creamos la cuanta especial para superar el limite
SpecialAccount = Account:new{cantidad = 100.00}

--establecemos el limite
s = SpecialAccount:new{limit=1000.00}

--hacemos un deposito
s:deposit(100.00)



--incluimos a la clase escepcional la nueva funcion para retirar dinero...
function SpecialAccount:withdraw(v)
    if v - self.cantidad >= self:getLimit() then
        error"saldo insuficiente"
    end
    self.cantidad = self.cantidad - v
end

--incluimos a la clase excepcional la nueva funcion para que nos muestre el limite de retiro
function SpecialAccount:getLimit()
    return self.limit or 0
end

--retiramos mas de lo que deberiamos
s:withdraw(300.00)

--nos mostrara el saldo negativo
print(s.cantidad)

Named = {}
function Named:getname ()
    return self.name
end

function Named:setname (n)
    self.name = n
end

-- Buscar 'k' en la lista 'plist'
local function search (k, plist)
    for i=1, #plist do
        local v = plist[i][k]   
        if v then
           return v
        end
    end
end

function createClass (...)
    local c = {}        --nueva clase
    local parents = {...}

--la clase buscará por cada metodo en la lista de sus padres
    setmetatable(c, {__index = function (t, k)
        return search(k, parents)
    end})

    --prepara 'c' para ser una metatabla de sus instancias.
    c.__index = c

    --define el nuevo constructor para esta nueva clase
    function c:new (o)
        o = o or {}
        setmetatable(o, c)
        return o
    end

    return c        --devuelve la nueva clase
    
end


--creamos NameAccount que herede de Account y Named
NamedAccount = createClass(Account, Named)

account = NamedAccount:new{name = "Paul"}
print(account:getname())--devuelve el nombre "Paul"

--Ahora si intentamos hacer lo mismo que anteriormente...
s = Account:new{cantidad = 100.00}--damos de alta una cuenta corriente con una cantidad inicial de 0.00$

s:withdraw(500.00)--retiramos 50.00$

print(s.cantidad)--nos dará la cantidad de 50$