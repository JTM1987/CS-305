# CS-305 Software Security — Portfolio (Jesse Martin)

Southern New Hampshire University · Artemis Financial client scenario

## Course artifact

**Artemis Financial Practices for Secure Software Report** (Project Two)

- File: [`artifacts/CS-305-Project-Two-Practices-for-Secure-Software-JMartin.docx`](artifacts/CS-305-Project-Two-Practices-for-Secure-Software-JMartin.docx)

This report documents secure refactoring of the Spring Boot `ssl-server` application: SHA-256 checksum verification at `/hash`, PKCS#12 TLS on port 8443, certificate export, OWASP Dependency-Check secondary testing, and manual functional review.

*(Project One Vulnerability Assessment Report was completed earlier in the course; this repository highlights the secure implementation artifact.)*

---

## Reflection

### Client, requirements, and the problem

Artemis Financial is a fictional fintech client that needed its public web services hardened as part of a modernization effort. The company cares about secure communication for customer data, integrity of data in transit, and reducing risk from known component vulnerabilities while still shipping working Java/Spring Boot services. In Project One I assessed an existing REST codebase without changing code. In Project Two I implemented fixes: cryptography for integrity, HTTPS for transport, and repeatable static analysis so the team can see whether dependencies still carry CVEs after changes.

### Finding vulnerabilities and why secure coding matters

I did my best work when I tied every finding to a concrete location (controller, properties file, or Maven coordinate) and to a process-flow area such as input validation, cryptography, or code quality. Dependency-Check helped surface legacy Spring Boot and transitive library CVEs that are easy to miss during a quick read of application code. Secure coding matters because a single weak endpoint or outdated JAR can become the path to data loss, fraud, or downtime. For Artemis, software security supports customer trust, reduces breach cost, and makes audits and partner due diligence easier.

### Challenging and helpful parts of vulnerability assessment

Mapping manual findings to the vulnerability assessment process flow was helpful because it kept the report organized for a non-developer stakeholder. The hardest part was prioritizing mitigation when static scans report hundreds of CVEs on an old Spring Boot baseline: not every line item is exploitable in context, but each one still needs a documented decision (upgrade, compensate, or accept with rationale). Learning to read Dependency-Check output and separate application code risk from dependency risk was one of the most useful skills in the course.

### Layers of security and future assessment

I added layers in Project Two by (1) enforcing HTTPS with a keystore-backed connector, (2) exposing SHA-256 checksum output for integrity checks, (3) running OWASP Dependency-Check 11.1.1 after refactor, and (4) performing manual review of controllers, TLS settings, and dependency choices. Going forward I would combine SCA tools (Dependency-Check or similar), framework security advisories, and targeted manual review for authentication, input bounds, and secrets handling. Mitigation choices would follow severity, exploitability, and whether the vulnerable component is reachable from the attack surface.

### Functional, secure code and regression checks

After refactoring I verified the application compiles, starts in Eclipse, and serves `https://localhost:8443/hash` with the expected digest for my unique test string. I re-ran Dependency-Check to confirm my Java changes did not add new Maven dependencies while still reporting the known baseline CVEs from the course stack. Manual review caught practical issues such as unused dependencies and the need for input length limits on the hash endpoint. That combination (run, browse, scan, read code) is how I would gate future changes before release.

### Resources and practices to reuse

Oracle Java security standard algorithm names, Java `keytool` keystore workflows, Spring Boot SSL properties, Maven Dependency-Check with an NVD API key, and structured manual review using the course process-flow diagram. I also relied on *Iron-Clad Java* for secure web application patterns. Habits I will keep: remove unused dependencies, keep controllers focused, bound user-controlled input, and document TLS and certificate choices instead of leaving placeholders in configuration files.

### What I would show employers

I would show this Practices for Secure Software report plus the refactored Maven project behavior: HTTPS endpoint, checksum implementation, certificate export, and Dependency-Check evidence. Together they demonstrate that I can assess risk, implement standard controls, and validate that changes still run while understanding third-party vulnerability reports. The Project One vulnerability assessment report is a strong companion piece for interviews that focus on assessment and mitigation planning rather than hands-on coding.
