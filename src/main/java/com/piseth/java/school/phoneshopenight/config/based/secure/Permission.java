package com.piseth.java.school.phoneshopenight.config.based.secure;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Permission {
	BRAND_WRITE("brand:write"),
	BRAND_READ("brand:read"),
	MODEL_WRITE("model:write"),
	MODEL_READ("model:read");
	private String description;
//	private Permission(String desc) {
//		// TODO Auto-generated constructor stub
//	}

}
