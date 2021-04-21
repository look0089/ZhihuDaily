package org.jzs.retrofit;


import org.jzs.entity.BaseEntity;

public interface TaskListener<T extends BaseEntity> {
	void taskStart();

	void taskStop();

	void taskSuccess(T basicBean);

	void taskFailure(T basicBean);

	void taskError(Throwable throwable);
}
