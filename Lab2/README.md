#Laboratorul 2: -output 
 - Compulosry :  Depot{name='Depot A'}
   
  Vehicle{name='Toyota', depot=Depot{name='Depot A'}, 
  
  clients=[Client{name='Client 1'}, Client{name='Client 2'}, Client{name='Client 3'}]}
  
  Client{name='Client 1'}

  Client{name='Client 2'}

  Client{name='Client 3'}
 - Homework :
   ```
   Vehicle{name='Mazada', depot=Depot A}
   Vehicle{name='Toyota', depot=Depot A}
   Vehicle{name='Tesla', depot=Depot A}
   Vehicle{name='Mazada', depot=Depot A}
   Client{name='Client 1'}
   Client{name='Client 2'}
   Client{name='Client 3'}
   Client{name='Client 3'}
   --Depot{name='Depot A', vehicles=[Vehicle{name='Toyota', depot=Depot A}, Vehicle{name='Mazada', depot=Depot A}, Vehicle{name='Tesla', depot=Depot A}, Vehicle{name='Mercedes', depot=Depot A}]'}
   --Depot{name='Depot B', vehicles=[Vehicle{name='Volvo', depot=Depot B}, Vehicle{name='Skoda', depot=Depot B}, Vehicle{name='Lamburghini', depot=Depot B}]'}
   --Depot{name='Depot C', vehicles=[Vehicle{name='Audi', depot=Depot C}, Vehicle{name='Ferari', depot=Depot C}]'}
   -------All Depots from problem--------
   [--Depot{name='Depot A', vehicles=[Vehicle{name='Toyota', depot=Depot A}, Vehicle{name='Mazada', depot=Depot A}, Vehicle{name='Tesla', depot=Depot A}, Vehicle{name='Mercedes', depot=Depot A}]'},
   --Depot{name='Depot B', vehicles= 
   [Vehicle{name='Volvo', depot=Depot B}, Vehicle{name='Skoda', depot=Depot B}, Vehicle{name='Lamburghini', depot=Depot B}]'},
   --Depot{name='Depot C', vehicles=[Vehicle{name='Audi', depot=Depot C}, Vehicle{name='Ferari', depot=Depot C}]'}, 
   --Depot{name='Depot D', vehicles=[]'}] ~ 
   -------All Vehicles from problem--------
   Toyota ,  Mazada ,  Tesla ,  Mercedes ,  Volvo ,  Skoda ,  Lamburghini ,  Audi ,  Ferari , 
   -----Tour------
   Tour{vehicle=Vehicle{name='Tesla', depot=Depot A}, clients=[Client{name='Client 2'}, Client{name='Client 1'}]}
   ```
