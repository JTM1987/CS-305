repackage com.snhu.sslserver;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Checksum endpoint. */
@RestController
public class HashController {

	// Limit query size.
	private static final int MAX_PARAM_LEN = 512;

	// Checksum route.
	@GetMapping("/hash")
	public ResponseEntity<String> checksum(
			@RequestParam(defaultValue = "JesseMartin") String name,
			@RequestParam(defaultValue = "Hello World Check Sum!") String input) {
		if (name.length() > MAX_PARAM_LEN || input.length() > MAX_PARAM_LEN) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("name and input must each be at most " + MAX_PARAM_LEN + " characters");
		}
		// Hash only the data string so manual verification tools match directly.
		String body = String.format("Name: %s%nData: %s%nSHA-256: %s", name, input, sha256Hex(input));
		return ResponseEntity.ok(body);
	}

	private String sha256Hex(String value) {
		try {
			// SHA-256 checksum.
generation.
stance("SHA-256");
			byte[] hashBytes = digest.digest(value.getBytes(StandardCharsets.UTF_8));
			StringBuilder hex = new StringBuilder();
			for (byte b : hashBytes) {
				hex.append(String.format("%02x", b));
			}
			return hex.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("SHA-256 is not available", e);
		}
	}
}
