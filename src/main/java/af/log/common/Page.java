package af.log.common;

import lombok.Data;

import java.util.List;

@Data
public class Page<E> {
    private Integer num;

    private List<E> list;
}
