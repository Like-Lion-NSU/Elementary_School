package thisisus.school.common.response;


import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageResponse<T> {
	private int totalPage;
	private Long totalElements;
	private int pagingSize;
	private int currentPage;
	private Boolean isFirst;
	private Boolean isLast;
	private Boolean isEmpty;
	private List<T> data;

	public static PageResponse of(Page page) {
		return PageResponse.builder()
			.totalPage(page.getTotalPages())
			.totalElements(page.getTotalElements())
			.pagingSize(page.getSize())
			.currentPage(page.getNumber() + 1)
			.isFirst(page.isFirst())
			.isLast(page.isLast())
			.isEmpty(page.isEmpty())
			.data(page.getContent())
			.build();
	}
}
