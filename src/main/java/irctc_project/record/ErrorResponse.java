package irctc_project.record;

public record ErrorResponse(
        String message,
        String code,
        boolean success
) {
}
