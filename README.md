# Prueba

Para esta tarea se debe crear una aplicación en el lenguajes de tu elección en el cual se debe
crear una aplicación que corra en la línea de comandos y manejara los nombres de las
personas en un archivo local que se llame fuzzy-search.txt, la aplicación debe tener las
siguientes operaciones:
* add
./application add <json>

  Agrega un nombre de persona en el archivo e imprimirá en pantalla:
  Usuario agregado
  Ejemplo de uso:
  ./application add {“name”: “Juan Antonio Perez”}
  Usuario agregado
  
 *  list
  ./application list
  Deberá de regresar todos los usuarios en orden alfabético que se encuentran en el archivo si
  no hubiera usuarios agregados regresará una lista vacía en el siguiente formato:
  ./application list
  []
  Si tuviera usuarios regresará en el siguiente formato:
  ./application list
  [
  {“name”: “Alberto Vera Padrón”},
  {“name”: “Juan Antonio Perez”},
  {“name”: “Rodolfo Juarez Fernandez”}
  ]

* search
  ./application fuzzy-search <json>

  Debe buscar dentro de los nombres del archivo y hacer un fuzzy search regresando el
  usuario con más aproximaciones, se debe definir un algoritmo de búsqueda de
  aproximación y explicar en el REAMDE de github el algoritmo y la razón de elegirlo.
  Ejemplo de uso:
  ./application fuzzy-search {“search”: “Alver”}
  {“name”: “Alberto Vera Padrón”}
  Si no hubiera coincidencias regresaría:
  ./application fuzzy-search {“search”: “Alver”}
  Sin coincidencias

# Instalación

* Bajar codigo

  1.- git clone https://github.com/rodmp/examen.git
  
  2.- git pull master 
  
* Compilacion

  cd ../app1
  
  mvn install
  
* Ejecución

  cd ../app1/target
  
  java -jar app1-0.0.1-SNAPSHOT-jar-with-dependencies Main add {"name":"Pedro Paramo"}
  
# Algoritmo de busqueda aproximada

Se trata de un algoritmo de tipo bottom-up, común en programación dinámica.
Se apoya en el uso de una matriz (n + 1) × (m + 1), donde n y m son las longitudes de las cadenas.
Aquí se indica el algoritmo en pseudocódigo para una función LevenshteinDistance que toma dos cadenas, 
str1 de longitud lenStr1, y str2 de longitud lenStr2, y calcula la distancia Levenshtein entre ellos:

int LevenshteinDistance(char str1[1..lenStr1], char str2[1..lenStr2])

   // d is a table with lenStr1+1 rows and lenStr2+1 columns

  declare int d[0..lenStr1, 0..lenStr2]

  // i and j are used to iterate over str1 and str2
   declare int i, j, cost
   
   for i from 0 to lenStr1
   
       d[i, 0] := i
       
   for j from 0 to lenStr2
   
       d[0, j] := j
 
   for i from 1 to lenStr1
   
       for j from 1 to lenStr2
       
           if str1[i-1] = str2[j-1] then cost := 0
           
                                else cost := 1
                                
           d[i, j] := minimum(
           
                                d[i-1, j] + 1,     // deletion
                                
                                d[i, j-1] + 1,     // insertion
                                
                                d[i-1, j-1] + cost   // substitution
                                
                            )
 
   return d[lenStr1, lenStr2]
   
   Se escogio este algoritmo por que nos da el menor número de cambios para convertir S1 a S2, acomplandolo a la busqueda nos queda 
   de la siguiente manera
   
   if(search.getSearch().length() < user.getName().length()) {
   
					value =  user.getName().length() - search.getSearch().length();
          
					s1 = user.getName().trim();
          
					s2 = search.getSearch().trim();
          
				}else if(user.getName().length() < search.getSearch().length()) {
        
					value = search.getSearch().length() - user.getName().length();
          
					s1 = search.getSearch().trim();
          
					s2 = user.getName().trim();
          
				}else {
        
					value = search.getSearch().length();
				}
 El valor de metric.distance(s1, s2) debe ser menor o igual value para que nos de los resultados mas próximos.       
