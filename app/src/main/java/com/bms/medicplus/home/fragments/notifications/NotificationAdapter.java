package com.bms.medicplus.home.fragments.notifications;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bms.medicplus.R;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemState;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionMoveToSwipedDirection;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractSwipeableItemViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> implements SwipeableItemAdapter<NotificationAdapter.MyViewHolder> {
    private static final String TAG = "NOTIFICATION_SWIPE";
    private List<Notifications> notifications;

    // NOTE: Make accessible with short name
    private interface Swipeable extends SwipeableItemConstants {
    }

    private EventListener mEventListener;
    private final View.OnClickListener mItemViewOnClickListener;
    private View.OnClickListener mSwipeableViewContainerOnClickListener;

    public interface EventListener {
        void onItemRemoved(int position);

        void onItemViewClicked(View v, boolean pinned);
    }


    public NotificationAdapter(List<Notifications> notifications) {
        this.notifications = notifications;
        mItemViewOnClickListener = this::onItemViewClick;
        setHasStableIds(true);
    }

    private void onItemViewClick(View v) {
        if (mEventListener != null) {
            mEventListener.onItemViewClicked(v, true); // true --- pinned
        }
    }


    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.notification_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        final Notifications item = notifications.get(position);

        // set listeners
        // (if the item is *pinned*, click event comes to the itemView)
        holder.itemView.setOnClickListener(mItemViewOnClickListener);
        // (if the item is *not pinned*, click event comes to the mContainer)
        holder.container.setOnClickListener(mSwipeableViewContainerOnClickListener);

        // set text
//        holder.mTextView.setText(item.getText());

        // set background resource (target view ID: container)
        final SwipeableItemState swipeState = holder.getSwipeState();

        if (swipeState.isUpdated()) {
            int bgResId;

            if (swipeState.isActive()) {
                bgResId = R.color.primaryDark;
            } else if (swipeState.isSwiping()) {
                bgResId = R.color.primary;
            } else {
                bgResId = R.color.transparent;
            }

            holder.container.setBackgroundResource(bgResId);
        }

        // set swiping properties
        holder.setSwipeItemHorizontalSlideAmount(Swipeable.OUTSIDE_OF_THE_WINDOW_LEFT);
    }

    @Override
    public long getItemId(int position) {
        // requires static value, it means need to keep the same value
        // even if the item position has been changed.
        return position;
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    @Override
    public int onGetSwipeReactionType(@NonNull @NotNull MyViewHolder holder, int position, int x, int y) {
        // Make swipeable to LEFT direction
        return Swipeable.REACTION_CAN_SWIPE_LEFT;
    }

    @Override
    public void onSwipeItemStarted(@NonNull @NotNull MyViewHolder holder, int position) {
        notifyDataSetChanged();
    }

    @Override
    public void onSetSwipeBackground(@NonNull @NotNull MyViewHolder holder, int position, int type) {
        int bgRes = 0;
        switch (type) {
            case Swipeable.DRAWABLE_SWIPE_NEUTRAL_BACKGROUND:
                bgRes = R.color.transparent;
                break;
            case Swipeable.DRAWABLE_SWIPE_LEFT_BACKGROUND:
            case Swipeable.DRAWABLE_SWIPE_RIGHT_BACKGROUND:
                bgRes = R.drawable.bg_delete_swipe;
                break;
        }

        holder.itemView.setBackgroundResource(bgRes);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public SwipeResultAction onSwipeItem(@NonNull @NotNull MyViewHolder holder, int position, int result) {
        Log.d(TAG, "onSwipeItem(position = " + position + ", result = " + result + ")");

        switch (result) {
            // swipe right
            case Swipeable.RESULT_SWIPED_RIGHT:
            case Swipeable.RESULT_SWIPED_LEFT:
                return new SwipeLeftResultAction(this, position);
            case Swipeable.RESULT_CANCELED:
            default:
                return null;
        }
    }

    public EventListener getEventListener() {
        return mEventListener;
    }

    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    private static class SwipeLeftResultAction extends SwipeResultActionMoveToSwipedDirection {
        private NotificationAdapter mAdapter;
        private final int mPosition;

        SwipeLeftResultAction(NotificationAdapter adapter, int position) {
            mAdapter = adapter;
            mPosition = position;
        }

        @Override
        protected void onPerformAction() {
            super.onPerformAction();

            // TODO MODIFY DATASET HERE
//            mAdapter.mProvider.removeItem(mPosition);
            mAdapter.notifyItemRemoved(mPosition);

        }

        @Override
        protected void onSlideAnimationEnd() {
            super.onSlideAnimationEnd();
        }

        @Override
        protected void onCleanUp() {
            super.onCleanUp();
            if (mAdapter.mEventListener != null) {
                mAdapter.mEventListener.onItemRemoved(mPosition);
            }
            // clear the references
            mAdapter = null;
        }
    }

    public static class MyViewHolder extends AbstractSwipeableItemViewHolder {
        FrameLayout container;

        public MyViewHolder(View view) {
            super(view);
            container = view.findViewById(R.id.container_view);
        }

        @NonNull
        @NotNull
        @Override
        public View getSwipeableContainerView() {
            return container;
        }
    }
}
