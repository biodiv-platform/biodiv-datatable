/**
 * 
 */
package com.strandls.dataTable;

/**
 * @author Abhishek Rudra
 *
 * 
 */
public class ApiConstants {

	/**
	 * since all the class members are static we need to add a private constructor
	 */
	private ApiConstants() {
		super();
	}

	// versioning
	public static final String V1 = "/v1";

	// ---------- paths ---------------
	public static final String PING = "/ping";
	public static final String SERVICES = "/services";
	public static final String SHOW = "/show";
	public static final String CREATE = "/create";
	public static final String UPDATE = "/update";
	public static final String LIST = "/list";

}
