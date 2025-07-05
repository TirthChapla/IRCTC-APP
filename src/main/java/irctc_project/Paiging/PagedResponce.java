package irctc_project.Paiging;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PagedResponce<T>
{

    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;

    public static <T> PagedResponce<T> fromPage(Page<T> page)
    {
        PagedResponce<T> response = new PagedResponce<>();

        response.setContent(page.getContent());

        response.setPage(page.getNumber());

        response.setSize(page.getSize());

        response.setTotalElements(page.getTotalElements());

        response.setTotalPages(page.getTotalPages());

        response.setLast(page.isLast());

        return response;
    }
}
