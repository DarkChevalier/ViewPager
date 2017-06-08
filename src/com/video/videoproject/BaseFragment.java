package com.video.videoproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Project: dnakeparkingmanager
 * @Package com.dnake.manager
 * @Title: BaseFragment.java
 * @Description: fragment基类（v4）
 * @author lxr
 * @version V1.0.0
 **/
@SuppressLint("NewApi")
public abstract class BaseFragment extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	protected void openFragment(int id, Fragment fragment, Bundle pBundle) {
		FragmentManager fragmentManager =getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		if (pBundle != null) {
			fragment.setArguments(pBundle);
		}
		transaction.replace(id, (Fragment) fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	protected void openFragment(int id, Fragment fragment) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(id, (Fragment) fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	protected void openFragmentFinish(int id, Fragment finishCurrentFragment,
			Fragment fragment, Bundle pBundle) {
		FragmentManager fragmentManager =getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.remove(finishCurrentFragment);
		if (pBundle != null) {
			fragment.setArguments(pBundle);
		}
		transaction.replace(id, (Fragment) fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	protected void openFragmentFinish(int id, Fragment finishCurrentFragment,
			Fragment fragment) {
		FragmentManager fragmentManager =getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.remove(finishCurrentFragment);
		transaction.replace(id, (Fragment) fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	
	protected void openActivity(Class<?> cls) {
		Intent intent = new Intent(getActivity(), cls);
		startActivity(intent);
	}
	
	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(getActivity(), pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}
	
	protected void defaultBackStack() {
		FragmentManager fragmentManager =getFragmentManager(); // 使用V4包，应使用FragmentActivity
		fragmentManager.popBackStack(); // 按back键，弹出fragment，恢复到之前的状态
	}

	public void defaultFinish() {
		getActivity().finish();
	}
	

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		super.setUserVisibleHint(isVisibleToUser);

		if (getUserVisibleHint()) {
			onVisible(true);
		} else {
			onVisible(false);
		}
	}

	/**
	 * 可见
	 * @param isVisible 
	 */
	protected void onVisible(boolean isVisible) {
		lazyLoad(isVisible);
	}

	/**
	 * 延迟加载 子类必须重写此方法
	 * @param isVisible 
	 */
	protected abstract void lazyLoad(boolean isVisible);


}
