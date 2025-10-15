package kr.co.team3.admin_repository;

import java.time.LocalDate;

public interface DashboardRepository {
    Object[] loadDashboardRaw(LocalDate fromDate, LocalDate toDate);
}
