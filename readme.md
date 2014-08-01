///////////////Connect to Postgres DB///////////////////////<br>
//vi du em co Postgres DB cai trong may: => se tao ra DB tren localhost voi default port 5432 <br>
//em co DB name la detektordaten_hessen wiederhergestellt tu backup file cua em => se co schema public + Tabelle mdp

1. install eclipse
2. download jdbc driver http://jdbc.postgresql.org/download.html
3. new java project -> project ->properties ->java Build Path ->libraries -> add External JARs... -> add JDBC Drivers in Project 
4. new void main class JDBC_connect_Example
5. see code from JDBC_connect_Example.java <br>
------------------------------------------------------------------------------<br>

///Tim van toc cua xe pkw va lkw < 40kmh////
+ cai nay don gian la em se viet 1 cau SQL: 
// vi du tim cho tat cac cac may do , toan bo time trong DB, trong do van toc cua LKW va PKW deu < 40kmh (sap xep theo ten may do tang dan, time tang dan):
SELECT 
	*
FROM 
	mdp
WHERE 
	(flow_lkw > 0 AND speed_lkw < 40 ) 
	AND (flow_pkw > 0 AND speed_pkw < 40 )
ORDER BY site asc, tsp asc
// vi du em chi muon query tren 1 vai may do thoi thi them vao SQL 1 Beschränkung:
SELECT 
	*
FROM 
	mdp
WHERE 
	(flow_lkw > 0 AND speed_lkw < 40 ) 
	AND (flow_pkw > 0 AND speed_pkw < 40 )
	AND site in (2000000,2000161)
ORDER BY site asc, tsp asc
+ trong java sau khi em chay query nay roi se dc 1 Resultset. Voi Resultset nay em co the ghi vao file(json,csv...) hoac dua len 1 Tabelle hien thi tren GUI
+ em xem trong Folder code 