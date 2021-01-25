# JavaCRUD-Gimnasio
##CRUD Java con Spring Web. JPA, Hibernate, Bootstrap, Thymeleaf, MySQL.

Proyecto MVC de sistema de administración de un gimnasio.

![Front](https://github.com/SJMartinez/JavaCRUD-Gimnasio/blob/main/Gym/front.png)
Primera versión:
Los miembros resaltados en rojo poseen una cuota vencida (la cuota vence a partir de 30 días de abonada) y deben volver a abonar para estar al día.
Se puede agregar, modificar y borrar miembros, todo en el contexto de una sola página HTML

![Front](https://github.com/SJMartinez/JavaCRUD-Gimnasio/blob/main/Gym/front2.png)

=======================
![Front](https://github.com/SJMartinez/JavaCRUD-Gimnasio/blob/main/Gym/front3.png)

Las cuotas se manejan en otra página

![Front](https://github.com/SJMartinez/JavaCRUD-Gimnasio/blob/main/Gym/front4.png)

El controlador maneja todos los algoritmos. Utilizo Thymeleaf para coordinar comunicaciones entre el controlador y el View, y para dinamismo de la página.
La arquitectura es en capas y las clases obedecen (o tratan de obedecer) principios SOLID y de POO.
Bases de datos con MySQL.
El proyecto está abierto a extensión, que iré subiendo a medida de completadas.

TODO:

<li>Lograr configurar correctamente los popovers del Bootstrap para un mejor dinamismo del Front</li>
<li>Limar algunas cosas del front que siento toscas</li>
<li>IMPORTANTE: Tilde en "membresías"</li>
<li>Extender el proyecto en:</li>
<li>capacidad de administrar empleados</li>
<li>valores de cuotas y sueldos de empleados, y algoritmos que realizen balances en base a ingresos y egresos de caja</li>
<li>Probar implementando Spring Security</li>
