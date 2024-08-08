package com.dragosghinea.royale.menus.locking;

public class RoyaleMenuLockingImpl implements RoyaleMenuLocking{

    private volatile int lockCount = 0;

    @Override
    public synchronized void lockClicking() {
        lockCount++;
    }

    @Override
    public synchronized void unlockClicking() {
        lockCount--;
    }

    @Override
    public synchronized boolean isLocked() {
        return lockCount> 0;
    }
}
