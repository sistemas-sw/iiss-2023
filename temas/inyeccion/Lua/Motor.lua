local Motor = {}
    
function Motor:new()
    local o = {}
    self.__index = self
    setmetatable(o, self)
    o.arrancar = function() self.turnOn = 1 self.marchas = 0 end
    o.apagar = function() self.turnOn = 0    end
    o.aumentarMarcha = function() self.marchas = self.marchas + 1  end
    o.disminuirMarcha = function() self.marchas = self.marchas - 1 end
    o.puntoMuerto = function() self.marchas = -1    end
    o.encendido = function() return self.turnOn end
    o.marchasMotor = function() return self.marchas end
    return o
end

--Utilizando las funciones set... permitimos que los usuarios puedan especificar sus propias implementacionespara cada operación definida en la clase
function Motor:setArrancar(arrancarFunc)
    self.arrancar = arrancarFunc
end

function Motor:setApagar(apagarFunc)
    self.apagar = apagarFunc
end

function Motor:setAumentarMarcha(aumentarMarchaFunc)
    self.aumentarMarcha = aumentarMarchaFunc
end

function Motor:setDisminuirMarcha(disminuirMarchaFunc)
    self.disminuirMarcha = disminuirMarchaFunc
end

function Motor:setPuntoMuerto(puntoMuertoFunc)
    self.puntoMuerto = puntoMuertoFunc
end


return Motor