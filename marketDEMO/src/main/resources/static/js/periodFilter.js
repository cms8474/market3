document.addEventListener("DOMContentLoaded", function() {
    window.onload = function() {
        const select = document.getElementsByName('recentMonths')[0];
        const currentDate = new Date(); // 현재 날짜

        for (let i = 0; i < 5; i++) {
            const month = new Date(currentDate.getFullYear(), currentDate.getMonth() - i, 1); // i개월 전 날짜 계산
            const optionValue = `${month.getFullYear()}-${month.getMonth() + 1}`
            const optionText = `${month.getFullYear()}년 ${month.getMonth() + 1}월`; // "2025년 9월" 형태로 표시

            const option = document.createElement('option');
            option.value = optionValue;
            option.textContent = optionText;

            select.appendChild(option); // select 요소에 option 추가
        }
    };

    // 폼 제출 시 validation 체크
    document.getElementsByClassName('search-period')[0].addEventListener("submit", function(event) {
        // 시작일과 종료일 체크 (둘 중 하나라도 입력되지 않으면 경고)
        const startDate = document.querySelector('input[name="startDate"]').value;
        const endDate = document.querySelector('input[name="endDate"]').value;

        if ((startDate && !endDate) || (!startDate && endDate)) {
            alert("시작일과 종료일을 모두 입력해주세요.");
            event.preventDefault();  // 폼 제출 막기
            return;
        }

        // 시작일이 종료일보다 큰 경우 경고 메시지
        if (startDate && endDate && new Date(startDate) > new Date(endDate)) {
            alert("시작일은 종료일보다 클 수 없습니다.");
            event.preventDefault();  // 폼 제출 막기
            return;
        }

        // 단위 버튼, 최근 개월수, 시작일~종료일 중 하나만 선택되도록 하기
        const unitSelected = document.querySelector('input[name="unit"]:checked');
        const recentMonths = document.querySelector('select[name="recentMonths"]').value;

        if (unitSelected) {
            // unit이 선택되면 recentMonths와 시작일, 종료일을 비워야 함
            document.querySelector('select[name="recentMonths"]').value = '';
            document.querySelector('input[name="startDate"]').value = '';
            document.querySelector('input[name="endDate"]').value = '';
        } else if (recentMonths) {
            // recentMonths가 선택되면 unit과 시작일, 종료일을 비워야 함
            const unitRadioButton = document.querySelector('input[name="unit"]:checked');
            if (unitRadioButton) {
                unitRadioButton.checked = false;
            }
            document.querySelector('input[name="startDate"]').value = '';
            document.querySelector('input[name="endDate"]').value = '';
        } else if (startDate && endDate) {
            // 시작일과 종료일이 선택되면 unit과 recentMonths를 비워야 함
            const unitRadioButton = document.querySelector('input[name="unit"]:checked');
            if (unitRadioButton) {
                unitRadioButton.checked = false;
            }
            document.querySelector('select[name="recentMonths"]').value = '';
        }
    });

    // 각 필드가 변경될 때 다른 필드를 초기화하는 부분
    document.querySelectorAll('input[name="unit"]').forEach(input => {
        input.addEventListener('change', function() {
            // unit 선택 시 recentMonths, startDate, endDate 초기화
            document.querySelector('select[name="recentMonths"]').value = '';
            document.querySelector('input[name="startDate"]').value = '';
            document.querySelector('input[name="endDate"]').value = '';
        });
    });

    document.querySelector('select[name="recentMonths"]').addEventListener('change', function() {
        // recentMonths 선택 시 unit, startDate, endDate 초기화
        document.querySelectorAll('input[name="unit"]').forEach(input => input.checked = false);
        document.querySelector('input[name="startDate"]').value = '';
        document.querySelector('input[name="endDate"]').value = '';
    });

    document.querySelectorAll('input[name="startDate"], input[name="endDate"]').forEach(input => {
        input.addEventListener('change', function() {
            // 시작일/종료일 선택 시 unit, recentMonths 초기화
            document.querySelectorAll('input[name="unit"]').forEach(input => input.checked = false);
            document.querySelector('select[name="recentMonths"]').value = '';
        });
    });
});

// 강민철 2025-10-20 1457