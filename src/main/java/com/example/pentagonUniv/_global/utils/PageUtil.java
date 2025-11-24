package com.example.pentagonUniv._global.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageUtil {
        private int currentPage; // 현재 페이지 (파라미터)
        private int totalCount; // 전체 데이터 개수
        private int pageSize; // 한 페이지당 보여줄 데이터 수
        private int blockSize; // 한 줄에 보여줄 페이지 번호 수

        private int totalPage; // 전체 페이지 개수
        private int startPage; // 현재 블록의 시작 페이지 번호
        private int endPage; // 현재 블록의 끝 페이지 번호

        private int prevPage; // 이전 블록의 시작 번호
        private int nextPage; // 다음 블록의 시작 번호

        private int offset; // DB LIMIT offset ~ pageSize 까지

        public PageUtil(int currentPage, int totalCount, int pageSize, int blockSize) {
                this.currentPage = currentPage;
                this.totalCount = totalCount;
                this.pageSize = pageSize;
                this.blockSize = blockSize;

                // 전체 페이지
                this.totalPage = (int) Math.ceil((double) totalCount / pageSize);

                // 현재 페이지 범위 보정
                if (currentPage < 1)
                        currentPage = 1;
                if (currentPage > totalPage)
                        currentPage = totalPage;

                // offset 계산
                this.offset = (currentPage - 1) * pageSize;

                // 블록 계산
                this.startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
                this.endPage = Math.min(startPage + blockSize - 1, totalPage);
        }
}
