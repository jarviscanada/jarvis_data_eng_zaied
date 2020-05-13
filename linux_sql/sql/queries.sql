--Q1
SELECT 
	cpu_number,
	max(id) AS id, 
	max(total_mem) AS total_mem 
FROM host_info 
GROUP BY cpu_number 
ORDER BY total_mem DESC;

--Q2
SELECT 
	id, 
	hostname,
	to_char( to_timestamp(round(extract(epoch from host_usage.timestamp) / 300) * 300), 'YYYY-MM-DD HH24:MI:SS') AS truncated_timestamp,
	(AVG(total_mem-memory_free)*100/total_mem)::INTEGER AS avg_used_mem_percentage 
FROM host_info 
INNER JOIN  host_usage ON host_info.id=host_usage.host_id 
GROUP BY truncated_timestamp, id 
ORDER BY id ASC;

--Q3
SELECT 
	host_id, 
	timestamp, 
	sl.failed_times FROM (SELECT host_id, timestamp, COUNT(*) FILTER (WHERE is_failed='failed') over (partition by host_id  order by timestamp desc) as failed_times from test_q3)sl 
WHERE failed_times=3;
