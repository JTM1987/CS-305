package com.snhu.sslserver;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Artemis file-verification step: expose a checksum clients can compare after download.
 * Refactored from the skeleton FIXME on SslServerApplication (moved here for separation).
 */
@RestController
public class HashController {

	// Oracle standard name from Java Security Standard Algorithm Names (SHA-2 family).
	private static final String HASH_ALGORITHM = "SHA-256";

	// Simple guard if this endpoint later accepts user input; keeps abuse surface small.
	private static final int MAX_DATA_LENGTH = 256;

	@GetMapping("/hash")
	public String hashChecksum() throws NoSuchAlgorithmException {
		// Unique string for rubric verification and screenshot evidence.
		String data = "Hello Jesse Martin!";

		if (data.length() > MAX_DATA_LENGTH) {
			throw new IllegalArgumentException("Input exceeds maximum allowed length.");
		}

		MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
		byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
		String checksum = bytesToHex(hashBytes);

		// HTML response format matches the course example for browser verification.
		return "<p>data:" + data
				+ "<p>Name of Cipher Algorithm Used : " + HASH_ALGORITHM
				+ " CheckSum Value:" + checksum;
	}

	// Course rubric asks for bytesToHex; kept local instead of pulling in Commons Codec.
	private static String bytesToHex(byte[] bytes) {
		StringBuilder hex = new StringBuilder(bytes.length * 2);
		for (byte value : bytes) {
			hex.append(String.format("%02x", value));
		}
		return hex.toString();
	}
}
