package com.strandls.dataTable.util;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.strandls.authentication_utility.util.AuthUtil;
import com.strandls.user.pojo.Role;
import com.strandls.user.pojo.User;

/**
 * @author Abhishek Rudra
 *
 */
public class TokenGenerator {

	public String generate(User user) {

		com.strandls.authentication_utility.model.User u1 = new com.strandls.authentication_utility.model.User();
		u1.setAboutMe(user.getAboutMe());
		u1.setAccountExpired(user.getAccountExpired());
		u1.setAccountLocked(user.getAccountLocked());
		u1.setDateCreated(user.getDateCreated());
		u1.setEmail(user.getEmail());
		u1.setEmailValidation(user.getEmailValidation());
		u1.setEnabled(user.getEnabled());
		u1.setHideEmial(user.getHideEmial());
		u1.setIcon(user.getIcon());
		u1.setId(user.getId());
		u1.setIdentificationMail(user.getIdentificationMail());
		u1.setInstitution(user.getInstitution());
		u1.setLanguageId(user.getLanguageId());
		u1.setLastLoginDate(user.getLastLoginDate());
		u1.setLatitude(user.getLatitude());
		u1.setLocation(user.getLocation());
		u1.setLongitude(user.getLongitude());
		u1.setMobileNumber(user.getMobileNumber());
		u1.setMobileValidation(user.getMobileValidation());
		u1.setName(user.getName());
		u1.setOccupation(user.getOccupation());
		u1.setPasswordExpired(user.getPasswordExpired());
		u1.setProfilePic(user.getProfilePic());
		Set<com.strandls.authentication_utility.model.Role> r1 = new HashSet<com.strandls.authentication_utility.model.Role>();
		for (Role role : user.getRoles()) {
			com.strandls.authentication_utility.model.Role r = new com.strandls.authentication_utility.model.Role();
			r.setAuthority(role.getAuthority());
			r.setId(role.getId());
			r.setVersion(role.getVersion());
			r1.add(r);

		}
		u1.setRoles(r1);
		u1.setSendDigest(user.getSendDigest());
		u1.setSendNotification(user.getSendNotification());
		u1.setSendPushNotification(user.getSendPushNotification());
		u1.setSexType(user.getSexType());

		Set<com.strandls.authentication_utility.model.FirebaseTokens> fb = new HashSet<com.strandls.authentication_utility.model.FirebaseTokens>();
		u1.setTokens(fb);
		u1.setUserName(user.getUserName());
		u1.setVersion(user.getVersion());

		Map<String, Object> token = AuthUtil.generateToken(u1, false);

		String accessToken = (String) token.get("access_token");
		accessToken = "Bearer " + accessToken;

		return accessToken;
	}

}
