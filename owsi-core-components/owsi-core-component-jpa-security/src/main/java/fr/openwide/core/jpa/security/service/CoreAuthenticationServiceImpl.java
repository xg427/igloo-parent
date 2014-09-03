package fr.openwide.core.jpa.security.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import fr.openwide.core.jpa.security.business.authority.util.CoreAuthorityConstants;

public class CoreAuthenticationServiceImpl implements IAuthenticationService {
	
	@Autowired
	private ISecurityService securityService;
	
	@Override
	public String getUserName() {
		return AuthenticationUtil.getUserName();
	}
	
	@Override
	public boolean isLoggedIn() {
		return AuthenticationUtil.isLoggedIn();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthenticationUtil.getAuthorities();
	}
	
	@Override
	public Collection<? extends Permission> getPermissions() {
		return securityService.getPermissions(AuthenticationUtil.getAuthentication());
	}

	@Override
	public boolean hasSystemRole() {
		return securityService.hasSystemRole(AuthenticationUtil.getAuthentication());
	}
	
	@Override
	public boolean hasAdminRole() {
		return securityService.hasAdminRole(AuthenticationUtil.getAuthentication());
	}
	
	@Override
	public boolean hasAuthenticatedRole() {
		return securityService.hasAuthenticatedRole(AuthenticationUtil.getAuthentication());
	}
	
	@Override
	public boolean hasRole(String role) {
		return securityService.hasRole(AuthenticationUtil.getAuthentication(), role);
	}
	
	@Override
	public boolean hasPermission(Object securedObject, Permission permission) {
		return securityService.hasPermission(AuthenticationUtil.getAuthentication(), securedObject, permission);
	}
	
	@Override
	public boolean hasPermission(Permission permission) {
		return securityService.hasPermission(AuthenticationUtil.getAuthentication(), permission);
	}
	
	@Override
	public boolean isAnonymousAuthority(String grantedAuthoritySid) {
		return CoreAuthorityConstants.ROLE_ANONYMOUS.equals(grantedAuthoritySid);
	}

	@Override
	public void signOut() {
		AuthenticationUtil.setAuthentication(null);
	}
	
	@Override
	public boolean isSuperUser() {
		return securityService.isSuperUser(AuthenticationUtil.getAuthentication());
	}
	
	protected Authentication getAuthentication() {
		return AuthenticationUtil.getAuthentication();
	}

}