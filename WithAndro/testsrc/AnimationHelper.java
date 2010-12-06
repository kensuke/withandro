/*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
//package org.kazzz.util;
package com.example;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * 繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ謠冗判縺ｮ縺溘ａ縺ｮ繝ｦ繝ｼ繝�ぅ繝ｪ繝�ぅ繧呈署萓帙＠縺ｾ縺� * 
 * @author Kazz.
 * @since JDK1.5 Android Level 4
 *
 */

public class AnimationHelper {
    /**
     * 蜿ｳ縺九ｉ蜈･縺｣縺ｦ縺上ｋ蜍穂ｽ懆｡ｨ迴ｾ縺吶ｋ繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ繧堤函謌舌�蜿門ｾ励＠縺ｾ縺�     * @return Animation 逕滓�縺励◆繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ縺梧綾繧翫∪縺�     */
    public static Animation inFromRightAnimation() {

        Animation inFromRight = 
            new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT
                    , +1.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f);
        inFromRight.setDuration(350);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }
    /**
     * 蟾ｦ縺ｫ騾夐℃縺励※縺�￥蜍穂ｽ懆｡ｨ迴ｾ縺吶ｋ繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ繧堤函謌舌�蜿門ｾ励＠縺ｾ縺�     * @return Animation 逕滓�縺励◆繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ縺梧綾繧翫∪縺�     */
    public static Animation outToLeftAnimation() {
        Animation outtoLeft = 
            new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , -1.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f);
        outtoLeft.setDuration(350);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }    
    /**
     * 蟾ｦ縺九ｉ蜈･縺｣縺ｦ縺上ｋ蜍穂ｽ懊ｒ陦ｨ迴ｾ縺吶ｋ繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ繧堤函謌舌�蜿門ｾ励＠縺ｾ縺�     * @return Animation 逕滓�縺励◆繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ縺梧綾繧翫∪縺�     */
    public static Animation inFromLeftAnimation() {
        Animation inFromLeft = 
            new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT
                    , -1.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f);
        inFromLeft.setDuration(350);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }
    /**
     * 蜿ｳ縺ｫ騾夐℃縺励※縺�￥蜍穂ｽ懊ｒ陦ｨ迴ｾ縺吶ｋ繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ繧堤函謌舌�蜿門ｾ励＠縺ｾ縺�     * @return Animation 逕滓�縺励◆繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ縺梧綾繧翫∪縺�     */
    public static Animation outToRightAnimation() {
        Animation outtoRight = 
            new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , +1.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f );
        outtoRight.setDuration(350);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }           

    /**
     * 荳翫°繧我ｸ九′縺｣縺ｦ縺上ｋ蜍穂ｽ懆｡ｨ迴ｾ縺吶ｋ繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ繧堤函謌舌�蜿門ｾ励＠縺ｾ縺�     * @return Animation 逕滓�縺励◆繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ縺梧綾繧翫∪縺�     */
    public static Animation inFromTopAnimation() {

        Animation inFromTop = 
            new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , -1.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f);
        inFromTop.setDuration(350);
        inFromTop.setInterpolator(new AccelerateInterpolator());
        return inFromTop;
    }
    /**
     * 荳九↓騾夐℃縺励※縺�￥蜍穂ｽ懆｡ｨ迴ｾ縺吶ｋ繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ繧堤函謌舌�蜿門ｾ励＠縺ｾ縺�     * @return Animation 逕滓�縺励◆繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ縺梧綾繧翫∪縺�     */
    public static Animation outToBottomAnimation() {
        Animation outToBottom = 
            new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , +1.0f);
        outToBottom.setDuration(350);
        outToBottom.setInterpolator(new AccelerateInterpolator());
        return outToBottom;
    }    

    /**
     * 荳九°繧画�縺｣縺ｦ蜈･縺｣縺ｦ縺上ｋ蜍穂ｽ懊ｒ陦ｨ迴ｾ縺吶ｋ繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ繧堤函謌舌�蜿門ｾ励＠縺ｾ縺�     * @return Animation 逕滓�縺励◆繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ縺梧綾繧翫∪縺�     */
    public static Animation inFromBottomAnimation() {
        Animation inFromBottom = 
            new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 1.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f);
        inFromBottom.setDuration(350);
        inFromBottom.setInterpolator(new AccelerateInterpolator());
        return inFromBottom;
    }
    /**
     * 荳翫↓騾夐℃縺励※縺�￥蜍穂ｽ懊ｒ陦ｨ迴ｾ縺吶ｋ繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ繧堤函謌舌�蜿門ｾ励＠縺ｾ縺�     * @return Animation 逕滓�縺励◆繧｢繝九Γ繝ｼ繧ｷ繝ｧ繝ｳ縺梧綾繧翫∪縺�     */
    public static Animation outToTopAnimation() {
        Animation outToTop = 
            new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , -1.0f );
        outToTop.setDuration(350);
        outToTop.setInterpolator(new AccelerateInterpolator());
        return outToTop;
    }           
}
