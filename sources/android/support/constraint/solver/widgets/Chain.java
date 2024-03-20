package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        if (i == 0) {
            int i4 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = i4;
            i3 = 0;
        } else {
            i3 = 2;
            int i5 = constraintWidgetContainer.mVerticalChainsSize;
            i2 = i5;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            ChainHead chainHead = chainHeadArr[i6];
            chainHead.define();
            if (!constraintWidgetContainer.optimizeFor(4)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            } else if (!Optimizer.applyChainOptimized(constraintWidgetContainer, linearSystem, i, i3, chainHead)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r2.mHorizontalChainStyle == 2) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
        if (r2.mVerticalChainStyle == 2) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004c, code lost:
        r5 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x038f  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x03a8  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x03ab  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x03b1  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0483  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x04b8  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x04dd  */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x04e2  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x04e8  */
    /* JADX WARNING: Removed duplicated region for block: B:270:0x04ed  */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x04f1  */
    /* JADX WARNING: Removed duplicated region for block: B:278:0x0503  */
    /* JADX WARNING: Removed duplicated region for block: B:293:0x0390 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0188  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0196  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r35, android.support.constraint.solver.LinearSystem r36, int r37, int r38, android.support.constraint.solver.widgets.ChainHead r39) {
        /*
            r0 = r35
            r9 = r36
            r1 = r39
            android.support.constraint.solver.widgets.ConstraintWidget r10 = r1.mFirst
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r1.mLast
            android.support.constraint.solver.widgets.ConstraintWidget r12 = r1.mFirstVisibleWidget
            android.support.constraint.solver.widgets.ConstraintWidget r13 = r1.mLastVisibleWidget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r1.mHead
            float r3 = r1.mTotalWeight
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r1.mFirstMatchConstraintWidget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r1.mLastMatchConstraintWidget
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r0.mListDimensionBehaviors
            r4 = r4[r37]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r7 = 1
            if (r4 != r5) goto L_0x0021
            r4 = 1
            goto L_0x0022
        L_0x0021:
            r4 = 0
        L_0x0022:
            r5 = 2
            if (r37 != 0) goto L_0x0038
            int r8 = r2.mHorizontalChainStyle
            if (r8 != 0) goto L_0x002b
            r8 = 1
            goto L_0x002c
        L_0x002b:
            r8 = 0
        L_0x002c:
            int r14 = r2.mHorizontalChainStyle
            if (r14 != r7) goto L_0x0032
            r14 = 1
            goto L_0x0033
        L_0x0032:
            r14 = 0
        L_0x0033:
            int r15 = r2.mHorizontalChainStyle
            if (r15 != r5) goto L_0x004c
            goto L_0x004a
        L_0x0038:
            int r8 = r2.mVerticalChainStyle
            if (r8 != 0) goto L_0x003e
            r8 = 1
            goto L_0x003f
        L_0x003e:
            r8 = 0
        L_0x003f:
            int r14 = r2.mVerticalChainStyle
            if (r14 != r7) goto L_0x0045
            r14 = 1
            goto L_0x0046
        L_0x0045:
            r14 = 0
        L_0x0046:
            int r15 = r2.mVerticalChainStyle
            if (r15 != r5) goto L_0x004c
        L_0x004a:
            r5 = 1
            goto L_0x004d
        L_0x004c:
            r5 = 0
        L_0x004d:
            r15 = r8
            r8 = r10
            r16 = r14
            r14 = r5
            r5 = 0
        L_0x0053:
            r21 = 0
            if (r5 != 0) goto L_0x012e
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r8.mListAnchors
            r7 = r7[r38]
            if (r4 != 0) goto L_0x0063
            if (r14 == 0) goto L_0x0060
            goto L_0x0063
        L_0x0060:
            r23 = 4
            goto L_0x0065
        L_0x0063:
            r23 = 1
        L_0x0065:
            int r24 = r7.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r7.mTarget
            if (r6 == 0) goto L_0x0077
            if (r8 == r10) goto L_0x0077
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r7.mTarget
            int r6 = r6.getMargin()
            int r24 = r24 + r6
        L_0x0077:
            r6 = r24
            if (r14 == 0) goto L_0x0085
            if (r8 == r10) goto L_0x0085
            if (r8 == r12) goto L_0x0085
            r24 = r3
            r23 = r5
            r3 = 6
            goto L_0x0095
        L_0x0085:
            if (r15 == 0) goto L_0x008f
            if (r4 == 0) goto L_0x008f
            r24 = r3
            r23 = r5
            r3 = 4
            goto L_0x0095
        L_0x008f:
            r24 = r3
            r3 = r23
            r23 = r5
        L_0x0095:
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r7.mTarget
            if (r5 == 0) goto L_0x00c2
            if (r8 != r12) goto L_0x00aa
            android.support.constraint.solver.SolverVariable r5 = r7.mSolverVariable
            r25 = r15
            android.support.constraint.solver.widgets.ConstraintAnchor r15 = r7.mTarget
            android.support.constraint.solver.SolverVariable r15 = r15.mSolverVariable
            r26 = r2
            r2 = 5
            r9.addGreaterThan(r5, r15, r6, r2)
            goto L_0x00b8
        L_0x00aa:
            r26 = r2
            r25 = r15
            android.support.constraint.solver.SolverVariable r2 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r7.mTarget
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            r15 = 6
            r9.addGreaterThan(r2, r5, r6, r15)
        L_0x00b8:
            android.support.constraint.solver.SolverVariable r2 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r7.mTarget
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            r9.addEquality(r2, r5, r6, r3)
            goto L_0x00c6
        L_0x00c2:
            r26 = r2
            r25 = r15
        L_0x00c6:
            if (r4 == 0) goto L_0x00fd
            int r2 = r8.getVisibility()
            r3 = 8
            if (r2 == r3) goto L_0x00ec
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r8.mListDimensionBehaviors
            r2 = r2[r37]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r3) goto L_0x00ec
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r38 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r8.mListAnchors
            r3 = r3[r38]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r5 = 5
            r6 = 0
            r9.addGreaterThan(r2, r3, r6, r5)
            goto L_0x00ed
        L_0x00ec:
            r6 = 0
        L_0x00ed:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            r2 = r2[r38]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            r3 = r3[r38]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r5 = 6
            r9.addGreaterThan(r2, r3, r6, r5)
        L_0x00fd:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r38 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x011e
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x011e
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            if (r3 == r8) goto L_0x011c
            goto L_0x011e
        L_0x011c:
            r21 = r2
        L_0x011e:
            if (r21 == 0) goto L_0x0125
            r8 = r21
            r5 = r23
            goto L_0x0126
        L_0x0125:
            r5 = 1
        L_0x0126:
            r3 = r24
            r15 = r25
            r2 = r26
            goto L_0x0053
        L_0x012e:
            r26 = r2
            r24 = r3
            r25 = r15
            if (r13 == 0) goto L_0x0158
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r11.mListAnchors
            int r3 = r38 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0158
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r5 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r11.mListAnchors
            r3 = r6[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            int r2 = r2.getMargin()
            int r2 = -r2
            r6 = 5
            r9.addLowerThan(r5, r3, r2, r6)
            goto L_0x0159
        L_0x0158:
            r6 = 5
        L_0x0159:
            if (r4 == 0) goto L_0x0175
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            int r2 = r38 + 1
            r0 = r0[r2]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r2]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r2 = r4[r2]
            int r2 = r2.getMargin()
            r4 = 6
            r9.addGreaterThan(r0, r3, r2, r4)
        L_0x0175:
            java.util.ArrayList<android.support.constraint.solver.widgets.ConstraintWidget> r0 = r1.mWeightedMatchConstraintsWidgets
            if (r0 == 0) goto L_0x022d
            int r2 = r0.size()
            r3 = 1
            if (r2 <= r3) goto L_0x022d
            boolean r4 = r1.mHasUndefinedWeights
            if (r4 == 0) goto L_0x018c
            boolean r4 = r1.mHasComplexMatchWeights
            if (r4 != 0) goto L_0x018c
            int r4 = r1.mWidgetsMatchCount
            float r4 = (float) r4
            goto L_0x018e
        L_0x018c:
            r4 = r24
        L_0x018e:
            r5 = 0
            r8 = r21
            r7 = 0
            r28 = 0
        L_0x0194:
            if (r7 >= r2) goto L_0x022d
            java.lang.Object r15 = r0.get(r7)
            android.support.constraint.solver.widgets.ConstraintWidget r15 = (android.support.constraint.solver.widgets.ConstraintWidget) r15
            float[] r3 = r15.mWeight
            r3 = r3[r37]
            int r23 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r23 >= 0) goto L_0x01c2
            boolean r3 = r1.mHasComplexMatchWeights
            if (r3 == 0) goto L_0x01be
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r15.mListAnchors
            int r23 = r38 + 1
            r3 = r3[r23]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r15 = r15.mListAnchors
            r15 = r15[r38]
            android.support.constraint.solver.SolverVariable r15 = r15.mSolverVariable
            r5 = 4
            r6 = 0
            r9.addEquality(r3, r15, r6, r5)
            r5 = 0
            r6 = 6
            goto L_0x01db
        L_0x01be:
            r5 = 4
            r3 = 1065353216(0x3f800000, float:1.0)
            goto L_0x01c3
        L_0x01c2:
            r5 = 4
        L_0x01c3:
            r6 = 0
            int r20 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r20 != 0) goto L_0x01e0
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r15.mListAnchors
            int r20 = r38 + 1
            r3 = r3[r20]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r15 = r15.mListAnchors
            r15 = r15[r38]
            android.support.constraint.solver.SolverVariable r15 = r15.mSolverVariable
            r5 = 0
            r6 = 6
            r9.addEquality(r3, r15, r5, r6)
        L_0x01db:
            r24 = r0
            r22 = r2
            goto L_0x0222
        L_0x01e0:
            r5 = 0
            r6 = 6
            if (r8 == 0) goto L_0x021b
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            r5 = r5[r38]
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r8.mListAnchors
            int r22 = r38 + 1
            r8 = r8[r22]
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r15.mListAnchors
            r6 = r6[r38]
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            r24 = r0
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r15.mListAnchors
            r0 = r0[r22]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            r22 = r2
            android.support.constraint.solver.ArrayRow r2 = r36.createRow()
            r27 = r2
            r29 = r4
            r30 = r3
            r31 = r5
            r32 = r8
            r33 = r6
            r34 = r0
            r27.createRowEqualMatchDimensions(r28, r29, r30, r31, r32, r33, r34)
            r9.addConstraint(r2)
            goto L_0x021f
        L_0x021b:
            r24 = r0
            r22 = r2
        L_0x021f:
            r28 = r3
            r8 = r15
        L_0x0222:
            int r7 = r7 + 1
            r2 = r22
            r0 = r24
            r3 = 1
            r5 = 0
            r6 = 5
            goto L_0x0194
        L_0x022d:
            if (r12 == 0) goto L_0x0297
            if (r12 == r13) goto L_0x0233
            if (r14 == 0) goto L_0x0297
        L_0x0233:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r10.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            int r2 = r38 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r10.mListAnchors
            r3 = r3[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x024e
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r10.mListAnchors
            r3 = r3[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0250
        L_0x024e:
            r3 = r21
        L_0x0250:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x0262
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r5 = r4
            goto L_0x0264
        L_0x0262:
            r5 = r21
        L_0x0264:
            if (r12 != r13) goto L_0x026e
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r2]
        L_0x026e:
            if (r3 == 0) goto L_0x04c9
            if (r5 == 0) goto L_0x04c9
            if (r37 != 0) goto L_0x0279
            r2 = r26
            float r2 = r2.mHorizontalBiasPercent
            goto L_0x027d
        L_0x0279:
            r2 = r26
            float r2 = r2.mVerticalBiasPercent
        L_0x027d:
            r4 = r2
            int r6 = r0.getMargin()
            int r7 = r1.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r8 = r1.mSolverVariable
            r10 = 5
            r0 = r36
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04c9
        L_0x0297:
            if (r25 == 0) goto L_0x0394
            if (r12 == 0) goto L_0x0394
            int r0 = r1.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x02a8
            int r0 = r1.mWidgetsCount
            int r1 = r1.mWidgetsMatchCount
            if (r0 != r1) goto L_0x02a8
            r17 = 1
            goto L_0x02aa
        L_0x02a8:
            r17 = 0
        L_0x02aa:
            r14 = r12
            r15 = r14
        L_0x02ac:
            if (r14 == 0) goto L_0x04c9
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r14.mNextChainWidget
            r0 = r0[r37]
            r8 = r0
        L_0x02b3:
            if (r8 == 0) goto L_0x02c2
            int r0 = r8.getVisibility()
            r7 = 8
            if (r0 != r7) goto L_0x02c4
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r8.mNextChainWidget
            r8 = r0[r37]
            goto L_0x02b3
        L_0x02c2:
            r7 = 8
        L_0x02c4:
            if (r8 != 0) goto L_0x02d1
            if (r14 != r13) goto L_0x02c9
            goto L_0x02d1
        L_0x02c9:
            r18 = r8
            r19 = 4
            r20 = 6
            goto L_0x0387
        L_0x02d1:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.SolverVariable r1 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            if (r2 == 0) goto L_0x02e0
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x02e2
        L_0x02e0:
            r2 = r21
        L_0x02e2:
            if (r15 == r14) goto L_0x02ed
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r15.mListAnchors
            int r3 = r38 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0304
        L_0x02ed:
            if (r14 != r12) goto L_0x0304
            if (r15 != r14) goto L_0x0304
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r10.mListAnchors
            r2 = r2[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0302
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r10.mListAnchors
            r2 = r2[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0304
        L_0x0302:
            r2 = r21
        L_0x0304:
            int r0 = r0.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r14.mListAnchors
            int r4 = r38 + 1
            r3 = r3[r4]
            int r3 = r3.getMargin()
            if (r8 == 0) goto L_0x0321
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            r5 = r5[r38]
            android.support.constraint.solver.SolverVariable r6 = r5.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r14.mListAnchors
            r7 = r7[r4]
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            goto L_0x0334
        L_0x0321:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r11.mListAnchors
            r5 = r5[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x032c
            android.support.constraint.solver.SolverVariable r6 = r5.mSolverVariable
            goto L_0x032e
        L_0x032c:
            r6 = r21
        L_0x032e:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r14.mListAnchors
            r7 = r7[r4]
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
        L_0x0334:
            if (r5 == 0) goto L_0x033b
            int r5 = r5.getMargin()
            int r3 = r3 + r5
        L_0x033b:
            if (r15 == 0) goto L_0x0346
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r15.mListAnchors
            r5 = r5[r4]
            int r5 = r5.getMargin()
            int r0 = r0 + r5
        L_0x0346:
            if (r1 == 0) goto L_0x02c9
            if (r2 == 0) goto L_0x02c9
            if (r6 == 0) goto L_0x02c9
            if (r7 == 0) goto L_0x02c9
            if (r14 != r12) goto L_0x0358
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r38]
            int r0 = r0.getMargin()
        L_0x0358:
            r5 = r0
            if (r14 != r13) goto L_0x0366
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r4]
            int r0 = r0.getMargin()
            r18 = r0
            goto L_0x0368
        L_0x0366:
            r18 = r3
        L_0x0368:
            if (r17 == 0) goto L_0x036d
            r22 = 6
            goto L_0x036f
        L_0x036d:
            r22 = 4
        L_0x036f:
            r4 = 1056964608(0x3f000000, float:0.5)
            r0 = r36
            r3 = r5
            r20 = 4
            r5 = r6
            r23 = 6
            r6 = r7
            r19 = 4
            r20 = 6
            r7 = r18
            r18 = r8
            r8 = r22
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x0387:
            int r0 = r14.getVisibility()
            r8 = 8
            if (r0 == r8) goto L_0x0390
            r15 = r14
        L_0x0390:
            r14 = r18
            goto L_0x02ac
        L_0x0394:
            r8 = 8
            r19 = 4
            r20 = 6
            if (r16 == 0) goto L_0x04c9
            if (r12 == 0) goto L_0x04c9
            int r0 = r1.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x03ab
            int r0 = r1.mWidgetsCount
            int r1 = r1.mWidgetsMatchCount
            if (r0 != r1) goto L_0x03ab
            r17 = 1
            goto L_0x03ad
        L_0x03ab:
            r17 = 0
        L_0x03ad:
            r14 = r12
            r15 = r14
        L_0x03af:
            if (r14 == 0) goto L_0x046b
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r14.mNextChainWidget
            r0 = r0[r37]
        L_0x03b5:
            if (r0 == 0) goto L_0x03c2
            int r1 = r0.getVisibility()
            if (r1 != r8) goto L_0x03c2
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r0.mNextChainWidget
            r0 = r0[r37]
            goto L_0x03b5
        L_0x03c2:
            if (r14 == r12) goto L_0x0458
            if (r14 == r13) goto L_0x0458
            if (r0 == 0) goto L_0x0458
            if (r0 != r13) goto L_0x03cd
            r7 = r21
            goto L_0x03ce
        L_0x03cd:
            r7 = r0
        L_0x03ce:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.SolverVariable r1 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            if (r2 == 0) goto L_0x03dc
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
        L_0x03dc:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r15.mListAnchors
            int r3 = r38 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            int r0 = r0.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r14.mListAnchors
            r4 = r4[r3]
            int r4 = r4.getMargin()
            if (r7 == 0) goto L_0x0404
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r7.mListAnchors
            r5 = r5[r38]
            android.support.constraint.solver.SolverVariable r6 = r5.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r5.mTarget
            if (r8 == 0) goto L_0x0401
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r5.mTarget
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            goto L_0x0417
        L_0x0401:
            r8 = r21
            goto L_0x0417
        L_0x0404:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r14.mListAnchors
            r5 = r5[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x040f
            android.support.constraint.solver.SolverVariable r6 = r5.mSolverVariable
            goto L_0x0411
        L_0x040f:
            r6 = r21
        L_0x0411:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r14.mListAnchors
            r8 = r8[r3]
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
        L_0x0417:
            if (r5 == 0) goto L_0x041e
            int r5 = r5.getMargin()
            int r4 = r4 + r5
        L_0x041e:
            r18 = r4
            if (r15 == 0) goto L_0x042b
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r15.mListAnchors
            r3 = r4[r3]
            int r3 = r3.getMargin()
            int r0 = r0 + r3
        L_0x042b:
            r3 = r0
            if (r17 == 0) goto L_0x0431
            r22 = 6
            goto L_0x0433
        L_0x0431:
            r22 = 4
        L_0x0433:
            if (r1 == 0) goto L_0x044f
            if (r2 == 0) goto L_0x044f
            if (r6 == 0) goto L_0x044f
            if (r8 == 0) goto L_0x044f
            r4 = 1056964608(0x3f000000, float:0.5)
            r0 = r36
            r5 = r6
            r6 = r8
            r23 = r7
            r7 = r18
            r18 = r15
            r15 = 8
            r8 = r22
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0455
        L_0x044f:
            r23 = r7
            r18 = r15
            r15 = 8
        L_0x0455:
            r0 = r23
            goto L_0x045c
        L_0x0458:
            r18 = r15
            r15 = 8
        L_0x045c:
            int r1 = r14.getVisibility()
            if (r1 == r15) goto L_0x0463
            goto L_0x0465
        L_0x0463:
            r14 = r18
        L_0x0465:
            r15 = r14
            r8 = 8
            r14 = r0
            goto L_0x03af
        L_0x046b:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r10.mListAnchors
            r1 = r1[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r38 + 1
            r10 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r11.mListAnchors
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r14 = r2.mTarget
            if (r1 == 0) goto L_0x04b8
            if (r12 == r13) goto L_0x0492
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            int r0 = r0.getMargin()
            r15 = 5
            r9.addEquality(r2, r1, r0, r15)
            goto L_0x04b9
        L_0x0492:
            r15 = 5
            if (r14 == 0) goto L_0x04b9
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r3 = r1.mSolverVariable
            int r4 = r0.getMargin()
            r5 = 1056964608(0x3f000000, float:0.5)
            android.support.constraint.solver.SolverVariable r6 = r10.mSolverVariable
            android.support.constraint.solver.SolverVariable r7 = r14.mSolverVariable
            int r8 = r10.getMargin()
            r17 = 5
            r0 = r36
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r17
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04b9
        L_0x04b8:
            r15 = 5
        L_0x04b9:
            if (r14 == 0) goto L_0x04c9
            if (r12 == r13) goto L_0x04c9
            android.support.constraint.solver.SolverVariable r0 = r10.mSolverVariable
            android.support.constraint.solver.SolverVariable r1 = r14.mSolverVariable
            int r2 = r10.getMargin()
            int r2 = -r2
            r9.addEquality(r0, r1, r2, r15)
        L_0x04c9:
            if (r25 != 0) goto L_0x04cd
            if (r16 == 0) goto L_0x0530
        L_0x04cd:
            if (r12 == 0) goto L_0x0530
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            int r2 = r38 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            if (r3 == 0) goto L_0x04e2
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x04e4
        L_0x04e2:
            r3 = r21
        L_0x04e4:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r1.mTarget
            if (r4 == 0) goto L_0x04ed
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r1.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x04ef
        L_0x04ed:
            r4 = r21
        L_0x04ef:
            if (r11 == r13) goto L_0x0500
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r4.mTarget
            if (r5 == 0) goto L_0x04fe
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x0500
        L_0x04fe:
            r4 = r21
        L_0x0500:
            r5 = r4
            if (r12 != r13) goto L_0x050b
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r2]
        L_0x050b:
            if (r3 == 0) goto L_0x0530
            if (r5 == 0) goto L_0x0530
            r4 = 1056964608(0x3f000000, float:0.5)
            int r6 = r0.getMargin()
            if (r13 != 0) goto L_0x0518
            goto L_0x0519
        L_0x0518:
            r11 = r13
        L_0x0519:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            r2 = r7[r2]
            int r7 = r2.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r8 = r1.mSolverVariable
            r10 = 5
            r0 = r36
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x0530:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):void");
    }
}
