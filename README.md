# CS-305 Software Security Reflection

***Jesse Martin: Southern New Hampshire University***
# ****Portfolio artifact submission****

## Client and Software Requirement
- # **Client:** Artemis Financial
- **Business issue:** Improve security for a public-facing financial web application
- **Primary requirement:** Implement secure communications and data integrity verification

## Work Completed and Security Value
- Refactored the application to run over HTTPS
- Implemented SHA-256 checksum verification
- Performed secondary testing with OWASP Dependency-Check
- Completed manual functional and security review
- Added value through stronger confidentiality, integrity, and reliability

## Challenging and Helpful Areas
- **Most challenging:** Certificate trust behavior in a local self-signed environment
- **Most helpful:** Following the vulnerability assessment process flow diagram for consistent validation and documentation

## Layers of Security and Future Assessment
- Added TLS configuration
- Generated/exported certificate artifacts
- Added checksum validation
- Ran dependency vulnerability scanning
- Performed post-change verification
- Future approach: continue using static analysis, dependency scanning, and risk-based mitigation

## Functional and Security Verification
- Ran Maven builds to confirm successful execution
- Validated secure endpoint at `https://localhost:8443/hash`
- Reviewed dependency-check reports
- Confirmed checksum output independently

## Resources, Tools, and Practices
- Java `keytool`
- Spring Boot SSL configuration
- OWASP Dependency-Check
- NVD references
- Iterative testing and review

## Employer-Facing Evidence
- Refactored secure codebase
- Completed secure software report
- Secure endpoint validation evidence
- Dependency scan outputs and mitigation discussion
##