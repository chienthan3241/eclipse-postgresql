///////////////Connect to Postgres DB///////////////////////
//vi du em co Postgres DB cai trong may: => se tao ra DB tren localhost voi default port 5432 
//em co DB name la detektordaten_hessen wiederhergestellt tu backup file cua em => se co schema public + Tabelle mdp

1. install eclipse
2. download jdbc driver http://jdbc.postgresql.org/download.html
3. new java project -> project ->properties ->java Build Path ->libraries -> add External JARs... -> add JDBC Drivers in Project 
4. new void main class JDBCExample
5. see code from JDBC_connect_Example.java 
////////////////////////////////////////////////////////////