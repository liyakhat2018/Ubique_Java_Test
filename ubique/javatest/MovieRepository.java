//Modify the createMovie and findMoviesByName methods of the class MovieRepository.

//•	Using JdbcTemplate, createMovie method should insert a new row into the movies table with the specified data.
//•	Using JdbcTemplate, findMoviesByName should return a list of all movies from the 'movies' table, in which the name of the movie contains the likeName string, using the LIKE operator. The returned list should contain Movie objects with name, year, and rating fields correctly set.

package com.ubique.javatest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

class Movie {
	public String name;
	public int year;
	public int rating;

	public Movie(String name, int year, int rating) {
		this.name = name;
		this.year = year;
		this.rating = rating;
	}
}

@Configuration
@Import(MovieRepository.class)
class Config {
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		return ds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DriverManagerDataSource ds) {
		return new JdbcTemplate(ds);
	}
}

@Repository
public class MovieRepository {

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void createTable() {
		template.execute(
				"CREATE TABLE movies (id bigint auto_increment primary key, name VARCHAR(50), year int, rating int)");
	}

	/*
	 * public void createMovie(String name, int year, int rating) {
	 * 
	 * }
	 */

	public void createMovie(String name, int year, int rating) {

		final String insertSql =

				"INSERT INTO movies (" +

						" name, " +

						" year, " +

						" rating) " +

						"VALUES (?, ?, ?, ?)";

		JdbcTemplate template = new JdbcTemplate(dataSource);

		// define query arguments
		Object[] params = new Object[] { name, year, rating };

		// define SQL types of the arguments
		int[] types = new int[] { Types.VARCHAR, Types.INTEGER, Types.INTEGER };

		// execute insert query to insert the data
		// return number of row / rows processed by the executed query
		int row = template.update(insertSql, params, types);
		System.out.println(row + " row inserted.");

	}

	public List<Movie> findMoviesByName(String name) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query("select name, year, rating from movies where name like ?", new Object[] { name },
				new RowMapper<Movie>() {
					@Override
					public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
						return new Movie(resultSet.getString("name"), resultSet.getInt("year"),
								resultSet.getInt("rating"));
					}
				});
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
		config.register(Config.class);
		config.refresh();
		MovieRepository repository = config.getBean(MovieRepository.class);

		repository.createMovie("Some movie", 1974, 3);
		repository.createMovie("Some other movie", 1993, 2);

		List<Movie> movies = repository.findMoviesByName("Some%");
		for (Movie movie : movies) {
			System.out.println(movie.name + " - " + movie.year + " - " + movie.rating);
		}
	}
}