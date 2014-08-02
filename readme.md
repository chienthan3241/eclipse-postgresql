///////////////Connect to Postgres DB///////////////////////<br>
//vi du em co Postgres DB cai trong may: => se tao ra DB tren localhost voi default port 5432 <br>
//em co DB name la detektordaten_hessen wiederhergestellt tu backup file cua em => se co schema public + Tabelle mdp

1. install eclipse
2. download jdbc driver http://jdbc.postgresql.org/download.html
3. new java project -> project ->properties ->java Build Path ->libraries -> add External JARs... -> add JDBC Drivers in Project 
4. new void main class JDBC_connect_Example
5. see code from JDBC_connect_Example.java <br>
------------------------------------------------------------------------------<br>

///Tìm vận tốc của xe pkw và lkw < 40kmh////
+ cái này đơn giản là em sẽ viết 1 câu SQL: <br>
// ví dụ tìm cho tất cả các máy đo , không giới hạn time, trong đó vận tốc của LKW và PKW đều < 40kmh (sắp xếp theo tên máy đo tăng dần, time tăng dần):<br>
SELECT 
	*
FROM 
	mdp
WHERE 
	(flow_lkw > 0 AND speed_lkw < 40 ) 
	AND (flow_pkw > 0 AND speed_pkw < 40 )
ORDER BY site asc, tsp asc <br>
// vi du em chi muon query tren 1 vai may do thoi thi them vao SQL 1 Beschränkung:<br>
SELECT 
	*
FROM 
	mdp
WHERE 
	(flow_lkw > 0 AND speed_lkw < 40 ) 
	AND (flow_pkw > 0 AND speed_pkw < 40 )
	AND site in (2000000,2000161)
ORDER BY site asc, tsp asc<br>
+ trong java sau khi em chay query nay roi se dc 1 Resultset. Voi Resultset nay em co the ghi vao file(xml,json,csv...) hoac dua len 1 Tabelle hien thi tren GUI
+ em xem trong code/sql_xkw_lower40kmh.java la vi du query du lieu tu DB roi ghi ra file duoi dang csv va json.
+ em xem trong code/mygui.java la vi du query du lieu tu DB roi ghi ra table tren swing Jtable (screenshot.jpg) (GUI em co the tao = WindowBuilder plugin)