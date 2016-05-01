package API;

import java.sql.ResultSet;
import java.sql.SQLException;

import redis.clients.jedis.Jedis;

public class Redis {

	private static Jedis jedis;

	@SuppressWarnings("deprecation")
	public static void ConnectRedis() {
		// Connecting to Redis server on localhost
		jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		// check whether server is running or not
		System.out.println("Server is running: " + jedis.ping());
		setJedis(jedis);
	}

	public static void statisticRedisDoctors() {

		ResultSet result = API.fcie.STATISTIC_Doctors();
		try {
			while (result.next()) {
				jedis.rpush("Doctors", "    " + result.getString(2) + " - "
						+ result.getString(1) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void statisticRedisAvgAge() {
		ResultSet result = API.fcie.STATISTIC_avg_age();
		try {
			while (result.next()) {
				jedis.rpush("avg_vek", result.getString(1) + " rokov\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void statisticRedisAgeGroup() {

		ResultSet result = API.fcie.STATISTIC_age_group();
		try {
			while (result.next()) {
				jedis.rpush(
						"age_group",
						"    od " + result.getString(1) + ": "
								+ result.getString(2) + ", poèet: "
								+ result.getString(3) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void statisticRedisProcedur() {

		ResultSet result = API.fcie.STATISTIC_procedur();
		try {
			while (result.next()) {
				jedis.rpush("procedure", result.getString(1) + " \n\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void statisticRedisMeno() {

		ResultSet result = API.fcie.STATISTIC_name();
		try {
			while (result.next()) {
				jedis.rpush("names", "    " + result.getString(1) + " - "
						+ result.getString(2) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void statisticRedisKlient() {

		ResultSet result = API.fcie.STATISTIC_klient();
		try {
			while (result.next()) {
				jedis.rpush("client", " " + result.getString(1) + " - "
						+ result.getString(2) + " pobyty/pobytov\n\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void clearStat() {
		jedis.del("client", "avg_vek", "names", "procedure", "age_group", "Doctors");
	}

	public static Jedis getJedis() {
		return jedis;
	}

	public static void setJedis(Jedis jedis) {
		Redis.jedis = jedis;
	}

}
