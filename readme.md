###Connect to Postgres DB
==============
- ví dụ em có Postgres DB cài trong máy: => sẽ tạo ra DB trên localhost với default port 5432
- em có DB name là detektordaten_hessen restore từ backup file của em => sẽ có schema public + Tabelle mdp + Tabelle mst
1. Install eclipse
2. Download jdbc driver http://jdbc.postgresql.org/download.html
3. New java project -> project ->properties ->java Build Path ->libraries -> add External JARs... -> add JDBC Drivers in Project 
4. New void main class JDBC_connect_Example
5. Xem code from JDBC_connect_Example.java 


###Tìm vận tốc của xe pkw và lkw < 40kmh:
==============
###### cái này đơn giản là em sẽ viết 1 câu SQL:
--------------
- ví dụ tìm cho tất cả các máy đo , không giới hạn time, trong đó vận tốc của LKW và PKW đều < 40kmh (sắp xếp theo tên máy đo tăng dần, time tăng dần):
``````SQL
SELECT * 
FROM  mdp 
WHERE 
(flow_lkw > 0 AND speed_lkw < 40 ) AND 
(flow_pkw > 0 AND speed_pkw < 40 ) 
ORDER BY site asc, tsp asc 
``````
- ví dụ em chỉ muốn query trên 1 vài máy đo thôi thi thêm vào SQL 1 điều kiện:
````SQL
SELECT * 
FROM mdp 
WHERE 
(flow_lkw > 0 AND speed_lkw < 40 ) AND 
(flow_pkw > 0 AND speed_pkw < 40 ) 
AND site in (2000000,2000161)
ORDER BY site asc, tsp asc
````
- trong java sau khi em chạy query này rồi sẽ được 1 Resultset. Với Resultset này em có thể ghi dữ liệu đã được lấy từ DB vào file( xml,json,csv...) hoặc đưa lên 1 Tabelle hiển thị trên GUI trong application của em.
- em xem file java trong code/sql_xkw_lower40kmh.java là ví dụ query dữ liệu từ DB rồi ghi ra file dưới dạng csv và json.
- em xem file java trong code/mygui.java là ví dụ query dữ liệu từ DB rồi ghi ra table trên GUI Jtable (screenshot.jpg) (GUI em có thể tạo rất dễ = WindowBuilder plugin link: http://www.eclipse.org/windowbuilder/)