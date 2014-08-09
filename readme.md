###Connect to Postgres DB###
+ Ví dụ em có Postgres DB cài trong máy: => sẽ tạo ra DB trên localhost với default port 5432
+ Em có DB name là detektordaten_hessen restore từ backup file của em => sẽ có schema public + Tabelle mdp + Tabelle mst

1. Install eclipse
2. Download jdbc driver http://jdbc.postgresql.org/download.html
3. New java project -> project ->properties ->java Build Path ->libraries -> add External JARs... -> add JDBC Drivers in Project 
4. New void main class JDBC_connect_Example
5. Xem code from [JDBC_connect_Example.java](https://github.com/chienthan3241/eclipse-postgresql/blob/master/code/JDBC_connect_Example.java)

___

###Tìm vận tốc của xe pkw và lkw < 40kmh:###
###### cái này đơn giản là em sẽ viết 1 câu SQL:######
> ví dụ tìm cho tất cả các máy đo , không giới hạn time, trong đó vận tốc của LKW và PKW đều < 40kmh (sắp xếp theo tên máy đo tăng dần, time tăng dần):

``````SQL
SELECT * 
FROM  mdp 
WHERE 
	(flow_lkw > 0 AND speed_lkw < 40 ) AND 
	(flow_pkw > 0 AND speed_pkw < 40 ) 
ORDER BY site asc, tsp asc 
``````
> ví dụ em chỉ muốn query trên 2 máy đo 2000000 và 2000161 thôi thì thêm vào SQL điều kiện:<br> 
> **AND site in (2000000,2000161)**

```````SQL
SELECT * 
FROM mdp 
WHERE 
	(flow_lkw > 0 AND speed_lkw < 40 ) AND 
	(flow_pkw > 0 AND speed_pkw < 40 ) AND 
	site in (2000000,2000161)
ORDER BY site asc, tsp asc
```````
- trong java sau khi em chạy query này rồi sẽ được 1 Resultset. Với Resultset này em có thể ghi dữ liệu đã được lấy từ DB vào file( xml,json,csv...) hoặc đưa lên 1 Tabelle hiển thị trên GUI trong application.
- em xem file java trong [code/sql_xkw_lower40kmh.java](https://github.com/chienthan3241/eclipse-postgresql/blob/master/code/sql_xkw_lower40kmh.java) là ví dụ query dữ liệu từ DB rồi ghi ra file dưới dạng csv và json.
- em xem file java trong [code/mygui.java](https://github.com/chienthan3241/eclipse-postgresql/blob/master/code/mygui.java) là ví dụ query dữ liệu từ DB rồi ghi ra table trên GUI Jtable ([screenshot.jpg](https://github.com/chienthan3241/eclipse-postgresql/blob/master/code/screenshot.jpg)) (GUI em có thể tạo rất dễ = WindowBuilder plugin: http://www.eclipse.org/windowbuilder/)

___

### Stoßwelle
> SQL tính khoảng cách giữa 2 máy đo: ví dụ máy P2011443 và P2011449 (http://postgis.refractions.net/docs/ST_Distance_Sphere.html)

`````SQL
SELECT
  ST_Distance_Sphere(
  ST_GeomFromText('POINT(' || (select ST_X(ST_TRANSFORM(coords_4326,4326)) FROM mst where site = 'R2011443') || (select ST_Y(ST_TRANSFORM(coords_4326,4326)) FROM mst where site = 'R2011443') || ')' ),
  ST_GeomFromText('POINT(' || (select ST_X(ST_TRANSFORM(coords_4326,4326)) FROM mst where site = 'R2011449') || (select ST_Y(ST_TRANSFORM(coords_4326,4326)) FROM mst where site = 'R2011449') || ')' )
    ) AS DISTANCE_IN_METER;
`````
> SQL tính density(/km) cho 18 máy đo trong Strecke chọn trước: sắp xếp theo vị trí của máy đo trên bản đồ, sắp xếp theo thời gian tăng giần, format của mật độ xe: 5 số cho phần nguyên, 2  số thập phân ví dụ: 12345.67

```````````````````````````````SQL
SELECT 
	site, 
	tsp, 
	flow_lkw, 
	speed_lkw, flow_pkw, speed_pkw, 
	(case when speed_lkw=0 then 0 else flow_lkw*60/speed_lkw::float end)::numeric(7,2) as density_lkw, 
	(case when speed_pkw=0 then 0 else flow_pkw*60/speed_pkw::float end)::numeric(7,2) as density_pkw
FROM mdp 
WHERE site in (2008002,2007853,2008259,2007956,2007865,2008265,2007871,2007938,2008313,2007932,2008307,2007889,2007920,2008301,2007895,2007914,2008295,2007902) 
ORDER BY 
  CASE 
  WHEN site = 2008002 THEN '1'
  WHEN site = 2007853 THEN '2'
  WHEN site = 2008259 THEN '3'
  WHEN site = 2007956 THEN '4'
  WHEN site = 2007865 THEN '5'
  WHEN site = 2008265 THEN '6'
  WHEN site = 2007871 THEN '7'
  WHEN site = 2007938 THEN '8'
  WHEN site = 2008313 THEN '9'
  WHEN site = 2007932 THEN '10'
  WHEN site = 2008307 THEN '11'
  WHEN site = 2007889 THEN '12'
  WHEN site = 2007920 THEN '13'
  WHEN site = 2008301 THEN '14'
  WHEN site = 2007895 THEN '15'
  WHEN site = 2007914 THEN '16'
  WHEN site = 2008295 THEN '17'
  WHEN site = 2007902 THEN '18'
  ELSE site END ASC, 
  tsp asc;
```````````````````````````````
+SQL ra kết quả rồi em sẽ lưu vào file ở json format : ví dụ calc_tmp.json (để khi tính toán Density Sprung thì không phải abfragen trên DB nữa vì DB chạy rất lâu)
> Tính những điểm với Density Sprung > x:

+ vói file calc_tmp.json ở trên em sẽ load dữ liệu vào 1 array trong java 
+ dùng vòng lặp trên array cho từng máy đo, so sánh density tại mỗi thời điểm với thời điểm kế tiếp (cách nhau 1 phút), nếu > x thì ghi lại
+ kết quả có thể hiện ra trên 1 table trong java, ghi vào file hoặc thể hiện trên đồ thị
+ Density Sprung có thể thay đổi và được xác định bởi User