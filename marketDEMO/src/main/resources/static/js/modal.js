// ===== 모달 열기닫기 =====
const openBtn = document.querySelector('.table-bottom .right .btn-blue');
const modal = document.getElementById('Modal');
const form = document.getElementById('Form');

function openModal() {
    if (!modal) return;
    modal.classList.add('is-open');
    modal.setAttribute('aria-hidden', 'false');
    document.body.classList.add('modal-open');
}

function closeModal() {
    if (!modal) return;
    modal.classList.remove('is-open');
    modal.setAttribute('aria-hidden', 'true');
    document.body.classList.remove('modal-open');
    form?.reset();
}

openBtn?.addEventListener('click', openModal);

modal?.addEventListener('click', (e) => {
    if (e.target.dataset.close === 'true') {
        closeModal();
    }
});
document.addEventListener('click', (e) => {
    const btn = e.target.closest('.btn-ship');
    if (btn) {
        const orderNo = btn.dataset.orderNo || '';
        document.getElementById('orderNo').value = orderNo;

        openModal();
    }
});


modal?.addEventListener('click', (e) => {
    if (e.target.dataset.close === 'true') closeModal();
});

document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape' && modal?.classList.contains('is-open')) {
        closeModal();
    }
});

// ===== 상세모달 열기/닫기 =====
const detailModal = document.getElementById('DetailModal');
const detailForm  = document.getElementById('detailForm'); // 만약 form이 있으면 id 부여하세요

function openDetailModal() {
    if (!detailModal) return;
    detailModal.classList.add('is-open');
    document.body.classList.add('modal-open');
}

function closeDetailModal() {
    if (!detailModal) return;
    detailModal.classList.remove('is-open');
    document.body.classList.remove('modal-open');
    detailForm?.reset(); // 폼 있으면 초기화
}

// 주문번호 클릭 → 상세 모달 오픈
document.addEventListener('click', (e) => {
    const btn = e.target.closest('.order-no a'); // <td class="order-no"><a ...>
    if (btn) {
        e.preventDefault();
        const orderNo = btn.dataset.orderNo || btn.textContent.trim();

        // 주문번호 값 세팅 (DetailModal 안에 <span id="od-orderNo"> 같은 곳)
        document.getElementById('od-orderNo').textContent = orderNo;

        openDetailModal();
    }
});

document.addEventListener('click', (e) => {
    const a = e.target.closest('td.coupon-link a');
    if (!a) return;
    e.preventDefault();

    const couponNo = a.dataset.couponNo || a.textContent.trim();


    // 본문 쿠폰번호 필드
    const bodyCouponNo = document.getElementById('coupon-no');
    if (bodyCouponNo) bodyCouponNo.textContent = couponNo;


    openDetailModal();
});

// 모달 바깥 영역/닫기버튼 클릭 시 닫기
detailModal?.addEventListener('click', (e) => {
    if (e.target.dataset.close === 'true') {
        closeDetailModal();
    }
});

// ESC 키로 닫기
document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape' && detailModal?.classList.contains('is-open')) {
        closeDetailModal();
    }
});
