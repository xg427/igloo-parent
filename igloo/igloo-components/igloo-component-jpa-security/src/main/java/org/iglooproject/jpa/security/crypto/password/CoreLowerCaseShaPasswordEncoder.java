package org.iglooproject.jpa.security.crypto.password;

import org.iglooproject.spring.util.StringUtils;

public class CoreLowerCaseShaPasswordEncoder extends CoreShaPasswordEncoder {

	public CoreLowerCaseShaPasswordEncoder(int strength) {
		super(strength);
	}
	
	@Override
	public String encode(CharSequence rawPassword) {
		return super.encode(StringUtils.lowerCase(rawPassword.toString()));
	}
	
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return super.matches(StringUtils.lowerCase(rawPassword.toString()), encodedPassword);
	}

}