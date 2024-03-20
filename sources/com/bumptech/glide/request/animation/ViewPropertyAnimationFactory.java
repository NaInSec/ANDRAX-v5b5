package com.bumptech.glide.request.animation;

import com.bumptech.glide.request.animation.ViewPropertyAnimation;

public class ViewPropertyAnimationFactory<R> implements GlideAnimationFactory<R> {
    private ViewPropertyAnimation<R> animation;
    private final ViewPropertyAnimation.Animator animator;

    public ViewPropertyAnimationFactory(ViewPropertyAnimation.Animator animator2) {
        this.animator = animator2;
    }

    public GlideAnimation<R> build(boolean z, boolean z2) {
        if (z || !z2) {
            return NoAnimation.get();
        }
        if (this.animation == null) {
            this.animation = new ViewPropertyAnimation<>(this.animator);
        }
        return this.animation;
    }
}
