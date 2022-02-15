package com.example.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Clothes;

@Repository
public class ClothesRepository {
	private static final RowMapper<Clothes> CLOTHES_ROW_MAPPER = (rs, i) -> {
		Clothes clothes = new Clothes();
		clothes.setId(rs.getInt("id"));
		clothes.setCategory(rs.getString("category"));
		clothes.setGenre(rs.getString("genre"));
		clothes.setGender(rs.getInt("gender"));
		clothes.setColor(rs.getString("color"));
		clothes.setPrice(rs.getInt("price"));
		clothes.setSize(rs.getString("size"));

		return clothes;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	public List<Clothes> searchByColorAndGender(String color, Integer gender) {
		String sql = "select * from clothes where color=:color and gender=:gender";
		SqlParameterSource param = new MapSqlParameterSource().addValue("color", color).addValue("gender", gender);
		List<Clothes> clothesList = template.query(sql, param, CLOTHES_ROW_MAPPER);
		return clothesList;

	}
}