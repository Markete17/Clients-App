package com.clients.restapi.auth;

public class JwtConfig {
	//Readme la información para generar estas claves
	
	public static final String SECRET_KEY ="alguna.clave.secreta.12345678";
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpAIBAAKCAQEA08hkk0EObGjDf2jEmCu23xtDXZ2whWZglYAgBal9pea2Gpdq\r\n"
			+ "2VvSXmxSr0moBKC0nEg38JNJSI4cBtLpqf3Mjn9trmcO9m82EEXbyvkZK6Ah2BPP\r\n"
			+ "zsHZ7w+Z8Cd1CqNaFNc0hTNewI26+AnezC7MZb1nKLmPOMhU2Z0dXGSnaA24qTeR\r\n"
			+ "UChMMtfYmEbffdl/4d2YujVwcFMTHDnLskvkllTtGm17KeWeOCCo/Ou78SSsQ9MT\r\n"
			+ "1KvDmuK+sKaCN9I5hTjp/UQZl6v855lTSLLb9WGG1yzSyqdkBDmiK/jgDJex4EsT\r\n"
			+ "yqiaoCuj2sQVYUhg0qaK23bpkYgXD38tSJg/uwIDAQABAoIBADSvtdWsaZoutfQM\r\n"
			+ "bFwvA9+dOLUhdbi6L20B4vWFpOyQ6NzUrpBEd6Fl8WwKNvJvyH7YuJHxEloqgG5y\r\n"
			+ "Kg9QRINu0CqDGQXVxO1nc7abIdfojoLi6t/U+hKvHuzP5B4xZUeqAjbhAMGGgO0U\r\n"
			+ "B4lkWxU0eWV4RFkr/W/l1lgV8k/AJg3+Nk7h0+6tel8RHDyKx2SrYBDlGCqFFtbO\r\n"
			+ "edFNqz09dKOw6d+SArnVmq4gcr78S8s2xXBV320F1jXwisYUKc9hsDPsRjINxrik\r\n"
			+ "l2tSHz+H6+71wMyWGhoNG5/i5KNzb5+0OwNgRiFvmrs7qAP9BIUoBxEUNcegDDg4\r\n"
			+ "iiqfaIECgYEA95cZzGS4CJq6vhM2nqZ1+igbgDUkxGscsmZkYTw4JybSU+cbwlEG\r\n"
			+ "bM73WrOye7FgJZB9gbA8dBGdSGdEeveEVPrul9RZTDxY0DSvJut63x+PEKPAkpRm\r\n"
			+ "mCoNnFDl22PmLXkOzliLbVm4dYv8BStg39IJ/XSZSNGaUE+XdAawrY8CgYEA2vnu\r\n"
			+ "eJem/L2GObnkXMOu1B30REqzEscz2Bt/PD0urO8/fcx69iONAX3h4vQiYCntDj65\r\n"
			+ "jID1uIDl8fTdXpL5e3jGbvZ5167ryFvQC3Ru1yASmILItL6Pk5M2fDsQ7/VxJ6a8\r\n"
			+ "OoYI7fBwupLDfX/v6XqCvDz8K34EEz5y3aK6TRUCgYEApbZkdgETi+7D/mQtspIH\r\n"
			+ "dJ9TiMQJD84BLwaJOOttSgcZzz5nve8tNbZwqCyN0WwuxfWnix3B2I3Rj5xOoV75\r\n"
			+ "G0e4Wbau7e+WIgShFpOVcbFVt/Vh9rmM0VuUMIOU1PpQHS9hUYrCBzZ9128SBwO5\r\n"
			+ "mxShyxo3Xh2sAwTCgc3YgtECgYAI/BJFzmsimaAlRDRTfg/xUh1QpAP+9D9zUYWB\r\n"
			+ "8jy1CcgAIGC3MrQ5aHHZPw0eBvpcCETSuAPdAyTsLZDbrYog/86chO5w5BN2kBXU\r\n"
			+ "Mn3Nw9ofedZEXEFF29dzd3T1c4umBdhZSmCZarElZpowzwwH92ff01zBaBVAuOI7\r\n"
			+ "p86fFQKBgQDFOn6w8nyWe1uGy8kuOvwnjQ1A3uyXiXj29Ira4Yu+ZNlbXqbSYikp\r\n"
			+ "2X2KSFdEJz/IJPn2UJUPnKRco+ZxWu9N9KGdPakhrzi9l02VTezHg+2ptVHeCVXu\r\n"
			+ "aDaDOBdrGON4ZUuAjspt72ThcR4gcs1Ndg5/SkI1LSQ85P23Dctlow==\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA08hkk0EObGjDf2jEmCu2\r\n"
			+ "3xtDXZ2whWZglYAgBal9pea2Gpdq2VvSXmxSr0moBKC0nEg38JNJSI4cBtLpqf3M\r\n"
			+ "jn9trmcO9m82EEXbyvkZK6Ah2BPPzsHZ7w+Z8Cd1CqNaFNc0hTNewI26+AnezC7M\r\n"
			+ "Zb1nKLmPOMhU2Z0dXGSnaA24qTeRUChMMtfYmEbffdl/4d2YujVwcFMTHDnLskvk\r\n"
			+ "llTtGm17KeWeOCCo/Ou78SSsQ9MT1KvDmuK+sKaCN9I5hTjp/UQZl6v855lTSLLb\r\n"
			+ "9WGG1yzSyqdkBDmiK/jgDJex4EsTyqiaoCuj2sQVYUhg0qaK23bpkYgXD38tSJg/\r\n"
			+ "uwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
	

}
