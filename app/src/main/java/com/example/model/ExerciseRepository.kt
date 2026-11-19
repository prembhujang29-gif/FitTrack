package com.example.model

object ExerciseRepository {

    val allExercises: List<Exercise> = listOf(
        // ==================== BEGINNER EXERCISES ====================
        Exercise(
            id = "walking",
            title = "Walking",
            category = ExerciseCategory.BEGINNER,
            difficulty = "Beginner",
            targetMuscles = listOf("Calves", "Hamstrings", "Quadriceps", "Cardiovascular"),
            equipment = "Comfortable Shoes",
            caloriesPerMinute = 5,
            defaultDurationSeconds = 300,
            overview = "A gentle, low-impact aerobic activity that promotes heart health, burns calories, and improves joint mobility.",
            drawableResName = "img_jogging",
            steps = listOf(
                ExerciseStep(1, "Stand Tall", "Keep head upright, shoulders relaxed and back, eyes looking forward about 15 feet ahead.", "Lengthen your spine and engage your core gently.", "walk_step_1"),
                ExerciseStep(2, "Heel Strike", "Step forward landing softly on your heel, rolling forward through the midfoot to the toes.", "Avoid slapping your feet hard against the ground.", "walk_step_2"),
                ExerciseStep(3, "Arm Swing", "Bend your elbows at roughly 90 degrees and swing arms naturally in sync with opposite legs.", "Keep wrists relaxed and hands lightly cupped.", "walk_step_3"),
                ExerciseStep(4, "Maintain Rhythm", "Breathe steadily in rhythm with your strides, keeping a brisk, conversational pace.", "Inhale for 3 steps, exhale for 3 steps.", "walk_step_4")
            ),
            correctPostureTips = listOf(
                "Keep spine elongated and chin parallel to the ground.",
                "Draw shoulder blades slightly down and back.",
                "Tighten the lower abdomen slightly to support your pelvis.",
                "Land softly from heel to toe on each stride."
            ),
            safetyTips = listOf(
                "Wear cushioned, supportive athletic footwear.",
                "Stay hydrated and avoid walking on uneven or slippery terrain.",
                "Warm up with 2 minutes of gentle pacing before speeding up."
            ),
            commonMistakes = listOf(
                "Looking down at your feet, causing forward neck strain.",
                "Overstriding by kicking the front foot too far forward.",
                "Slouching shoulders and letting arms dangle rigidly."
            )
        ),
        Exercise(
            id = "jogging",
            title = "Jogging",
            category = ExerciseCategory.BEGINNER,
            difficulty = "Beginner",
            targetMuscles = listOf("Quadriceps", "Glutes", "Calves", "Heart & Lungs"),
            equipment = "Running Shoes",
            caloriesPerMinute = 9,
            defaultDurationSeconds = 180,
            overview = "An effective aerobic exercise that builds cardiovascular stamina, burns high calories, and strengthens bone density.",
            drawableResName = "img_jogging",
            steps = listOf(
                ExerciseStep(1, "Forward Lean", "Hinge slightly forward from the ankles, keeping torso upright and core engaged.", "Do not bend at the waist; lean gently from the ankles.", "jog_step_1"),
                ExerciseStep(2, "Midfoot Strike", "Land lightly on your midfoot directly underneath your hips rather than out in front.", "Maintain a quick, light foot turnover cadence.", "jog_step_2"),
                ExerciseStep(3, "Knee Drive", "Lift knees moderately to a comfortable height without over-striding.", "Keep knees tracking straight ahead.", "jog_step_3"),
                ExerciseStep(4, "Rhythmic Breathing", "Inhale deeply through nose and mouth, matching your breaths to your step cadence.", "Aim for a 2:2 breath pattern.", "jog_step_4")
            ),
            correctPostureTips = listOf(
                "Keep chest proud, shoulders down, and jaw relaxed.",
                "Maintain arms bent at 90 degrees, swinging back and forth without crossing midline.",
                "Keep pelvis neutral without excessive arching in the lower back."
            ),
            safetyTips = listOf(
                "Gradually increase duration and pace to protect knees and shin bones.",
                "Cool down with 2 minutes of walking and calf stretches afterwards.",
                "Stop if you experience sharp knee, ankle, or chest pain."
            ),
            commonMistakes = listOf(
                "Heavy heel striking far ahead of the center of gravity.",
                "Tensing up shoulders toward ears.",
                "Holding breath or shallow mouth-only breathing."
            )
        ),
        Exercise(
            id = "jumping_jacks",
            title = "Jumping Jacks",
            category = ExerciseCategory.BEGINNER,
            difficulty = "Beginner",
            targetMuscles = listOf("Calves", "Deltoids", "Abductors", "Cardio"),
            equipment = "No Equipment",
            caloriesPerMinute = 8,
            defaultDurationSeconds = 60,
            overview = "A dynamic full-body plyometric drill that rapidly increases heart rate, improves coordination, and warms up the whole body.",
            drawableResName = "img_jogging",
            steps = listOf(
                ExerciseStep(1, "Starting Position", "Stand straight with feet together, arms resting comfortably by your sides.", "Brace your core and soften your knees.", "jack_step_1"),
                ExerciseStep(2, "Jump & Spread", "Bend knees slightly and jump upward, spreading feet outward shoulder-width apart.", "Simultaneously swing arms up and overhead in an arc.", "jack_step_2"),
                ExerciseStep(3, "Overhead Touch", "Clap or bring hands near each other above your head while landing softly on balls of feet.", "Land with slightly bent knees to absorb impact.", "jack_step_3"),
                ExerciseStep(4, "Return & Repeat", "Quickly jump back to the starting stance, bringing hands back down to sides smoothly.", "Continue in a fluid, bouncy rhythm.", "jack_step_4")
            ),
            correctPostureTips = listOf(
                "Keep your core activated to protect the lumbar spine.",
                "Always land softly on the balls of your feet with knees flexible.",
                "Keep arms fully extended during the overhead arc."
            ),
            safetyTips = listOf(
                "For low-impact modification, step one foot out to the side instead of jumping.",
                "Perform on a resilient surface or exercise mat."
            ),
            commonMistakes = listOf(
                "Landing with locked, stiff knees.",
                "Arching the lower back when raising arms overhead.",
                "Letting knees cave inward when landing."
            )
        ),
        Exercise(
            id = "marching_in_place",
            title = "Marching in Place",
            category = ExerciseCategory.BEGINNER,
            difficulty = "Beginner",
            targetMuscles = listOf("Hip Flexors", "Quadriceps", "Core", "Calves"),
            equipment = "No Equipment",
            caloriesPerMinute = 6,
            defaultDurationSeconds = 90,
            overview = "A low-impact cardio movement that engages the hip flexors, activates core stabilizers, and elevates circulation safely.",
            drawableResName = "img_jogging",
            steps = listOf(
                ExerciseStep(1, "Upright Stance", "Stand tall with feet hip-width apart, arms bent at 90 degrees.", "Tuck pelvis slightly and draw navel toward spine.", "march_step_1"),
                ExerciseStep(2, "Lift Right Knee", "Drive your right knee up toward hip height while pumping your left arm forward.", "Keep toes of the lifted foot pulled upward.", "march_step_2"),
                ExerciseStep(3, "Lower Softly", "Lower right foot with control, landing softly on the midfoot.", "Maintain balance over supporting leg.", "march_step_3"),
                ExerciseStep(4, "Alternate Left Knee", "Instantly lift left knee up while driving right arm forward in smooth cadence.", "Keep core engaged to avoid leaning back.", "march_step_4")
            ),
            correctPostureTips = listOf(
                "Maintain a vertical posture without tilting your torso backward.",
                "Drive knees to approximate 90-degree hip angle if flexibility allows.",
                "Engage glutes on the standing support leg."
            ),
            safetyTips = listOf(
                "Hold onto the back of a sturdy chair if balance is compromised.",
                "March at a controlled tempo before increasing speed."
            ),
            commonMistakes = listOf(
                "Leaning backward as knees lift.",
                "Slapping feet onto the floor instead of gentle shock-absorbing landings."
            )
        ),

        // ==================== UPPER BODY ====================
        Exercise(
            id = "pushups",
            title = "Push-ups",
            category = ExerciseCategory.UPPER_BODY,
            difficulty = "Intermediate",
            targetMuscles = listOf("Pectorals (Chest)", "Anterior Deltoids", "Triceps", "Core"),
            equipment = "Mat (Optional)",
            caloriesPerMinute = 9,
            defaultDurationSeconds = 60,
            overview = "The quintessential upper-body bodyweight exercise that builds chest thickness, shoulder stability, tricep power, and core endurance.",
            drawableResName = "img_pushups",
            steps = listOf(
                ExerciseStep(1, "High Plank Setup", "Place hands slightly wider than shoulder-width, wrists under shoulders, legs extended.", "Squeeze glutes and brace abs into a rigid straight line.", "pushup_step_1"),
                ExerciseStep(2, "Descent Phase", "Lower your body by bending elbows at a 45-degree angle to your ribs until chest hovers just above floor.", "Inhale as you lower over 2 controlled seconds.", "pushup_step_2"),
                ExerciseStep(3, "Bottom Pause", "Hold for a split second at the bottom with chest 1-2 inches above the ground.", "Keep neck neutral, looking 6 inches in front of hands.", "pushup_step_3"),
                ExerciseStep(4, "Press to Lockout", "Exhale and push the floor away powerfully until arms return straight without locking joints.", "Maintain rigid plank line throughout.", "pushup_step_4")
            ),
            correctPostureTips = listOf(
                "Straight line from head through hips down to ankles.",
                "Elbows tracking backward at a 45-degree angle, not flared 90 degrees.",
                "Retract scapulae smoothly on descent, protract gently at top."
            ),
            safetyTips = listOf(
                "Drop knees to floor or elevate hands on a bench if form breaks down.",
                "Keep neck neutral—do not crane your head down to touch chin first."
            ),
            commonMistakes = listOf(
                "Sagging hips or lower back sway (core disengagement).",
                "Flaring elbows straight out to sides, stressing rotator cuffs.",
                "Incomplete range of motion (half reps)."
            )
        ),
        Exercise(
            id = "wall_pushups",
            title = "Wall Push-ups",
            category = ExerciseCategory.UPPER_BODY,
            difficulty = "Beginner",
            targetMuscles = listOf("Chest", "Front Shoulders", "Triceps"),
            equipment = "Sturdy Wall",
            caloriesPerMinute = 5,
            defaultDurationSeconds = 60,
            overview = "An accessible vertical variation of the push-up that reduces joint stress while teaching proper scapular and pressing mechanics.",
            drawableResName = "img_pushups",
            steps = listOf(
                ExerciseStep(1, "Wall Setup", "Stand facing wall about arm's length away. Place palms flat at shoulder height and width.", "Step feet back slightly to create a modest forward angle.", "wall_step_1"),
                ExerciseStep(2, "Controlled Incline", "Bend elbows and lean whole body toward wall until nose nearly touches surface.", "Keep body in one straight line from head to heels.", "wall_step_2"),
                ExerciseStep(3, "Press Away", "Push firmly through palms and fingers to straighten arms back to starting position.", "Exhale and engage chest muscles at top.", "wall_step_3"),
                ExerciseStep(4, "Adjust Intensity", "Step feet further back to increase difficulty, or closer to reduce resistance.", "Keep heels grounded if possible for calf stretch.", "wall_step_4")
            ),
            correctPostureTips = listOf(
                "Keep shoulders pressed away from your ears.",
                "Brace abs gently so hips move in unison with shoulders."
            ),
            safetyTips = listOf(
                "Ensure wall surface is clean and shoes have good floor grip."
            ),
            commonMistakes = listOf(
                "Bending at the hips instead of maintaining a straight plank.",
                "Pressing from fingertips instead of full palm contact."
            )
        ),
        Exercise(
            id = "shoulder_press",
            title = "Shoulder Press",
            category = ExerciseCategory.UPPER_BODY,
            difficulty = "Beginner",
            targetMuscles = listOf("Deltoids", "Upper Trapezius", "Triceps", "Upper Chest"),
            equipment = "Light Dumbbells / Water Bottles / Bodyweight",
            caloriesPerMinute = 7,
            defaultDurationSeconds = 60,
            overview = "Strengthens overhead pushing power, improves shoulder stability, and sculpts athletic deltoids.",
            drawableResName = "img_pushups",
            steps = listOf(
                ExerciseStep(1, "Starting Stance", "Stand tall with feet shoulder-width, bring hands/weights to shoulder level with elbows bent 90 degrees.", "Palms facing forward, core braced, chest lifted.", "press_step_1"),
                ExerciseStep(2, "Drive Overhead", "Exhale as you press hands upward in a smooth arc until arms are extended overhead.", "Biceps should align near ears at the top.", "press_step_2"),
                ExerciseStep(3, "Top Lockout", "Hold overhead for a moment without shrugging shoulders up into your neck.", "Keep ribs pulled down and spine neutral.", "press_step_3"),
                ExerciseStep(4, "Control Descent", "Inhale as you lower hands slowly back down to ear/shoulder height over 2-3 seconds.", "Resist the weight on the way down.", "press_step_4")
            ),
            correctPostureTips = listOf(
                "Do not arch your lower back to hoist the weight overhead.",
                "Keep glutes and abs tight during the entire overhead extension."
            ),
            safetyTips = listOf(
                "Use manageable weight; avoid dropping elbows below shoulder plane excessively."
            ),
            commonMistakes = listOf(
                "Excessive lumbar extension (leaning backward).",
                "Locking elbow joints forcefully at peak height."
            )
        ),
        Exercise(
            id = "arm_circles",
            title = "Arm Circles",
            category = ExerciseCategory.UPPER_BODY,
            difficulty = "Beginner",
            targetMuscles = listOf("Deltoids", "Rotator Cuff", "Upper Back", "Biceps"),
            equipment = "No Equipment",
            caloriesPerMinute = 4,
            defaultDurationSeconds = 60,
            overview = "Dynamic shoulder mobilization exercise that lubricates joints, increases blood flow, and tones shoulder caps.",
            drawableResName = "img_stretching",
            steps = listOf(
                ExerciseStep(1, "Extend Arms T-Shape", "Stand tall with feet hip-width. Extend arms straight out to sides at shoulder height, palms down.", "Fingertips reaching outward, shoulders down.", "circle_step_1"),
                ExerciseStep(2, "Small Forward Circles", "Begin rotating arms forward in small, controlled 6-inch circles for 30 seconds.", "Maintain constant shoulder tension and straight elbows.", "circle_step_2"),
                ExerciseStep(3, "Medium to Large Circles", "Gradually widen circle radius to open chest and shoulder capsule.", "Breathe steadily in and out.", "circle_step_3"),
                ExerciseStep(4, "Reverse Direction", "Flip palms upward and circle backwards for the remaining duration.", "Squeeze shoulder blades together on each backward swing.", "circle_step_4")
            ),
            correctPostureTips = listOf(
                "Keep arms parallel to floor without drooping.",
                "Keep neck long and shoulders relaxed away from ears."
            ),
            safetyTips = listOf(
                "Make circles smaller if you feel shoulder pinching or clicking."
            ),
            commonMistakes = listOf(
                "Shrugging shoulders into ears.",
                "Letting arms drop below horizontal level."
            )
        ),

        // ==================== LOWER BODY ====================
        Exercise(
            id = "squats",
            title = "Squats",
            category = ExerciseCategory.LOWER_BODY,
            difficulty = "Beginner",
            targetMuscles = listOf("Quadriceps", "Gluteus Maximus", "Hamstrings", "Calves", "Core"),
            equipment = "No Equipment",
            caloriesPerMinute = 8,
            defaultDurationSeconds = 60,
            overview = "The king of lower body movements. Squats build functional leg strength, firm glutes, and improve athletic power.",
            drawableResName = "img_squats",
            steps = listOf(
                ExerciseStep(1, "Foot Placement", "Stand with feet slightly wider than shoulder-width, toes turned out 15-30 degrees.", "Extend arms forward for counter-balance or hold hands at chest.", "squat_step_1"),
                ExerciseStep(2, "Hip Hinge & Descent", "Inhale, initiate movement by sending hips back, then bend knees smoothly.", "Drive knees outward in line with your toes.", "squat_step_2"),
                ExerciseStep(3, "Depth Target", "Descend until thighs are parallel to the floor (or comfortably deep with flat spine).", "Keep weight centered over midfoot and heels.", "squat_step_3"),
                ExerciseStep(4, "Drive Through Heels", "Exhale, press floor away through heels and midfoot, driving hips forward to stand.", "Squeeze glutes at top without hyperextending spine.", "squat_step_4")
            ),
            correctPostureTips = listOf(
                "Chest stays upright, gaze forward.",
                "Knees track over the second and third toes without caving in.",
                "Maintain natural curve of the lumbar spine without rounding."
            ),
            safetyTips = listOf(
                "Do not allow heels to lift off floor.",
                "Perform box squats onto a chair if balance is challenging."
            ),
            commonMistakes = listOf(
                "Knees collapsing inward (valgus collapse).",
                "Rounding the lower back ('butt wink') at bottom.",
                "Shifting weight excessively onto toes."
            )
        ),
        Exercise(
            id = "lunges",
            title = "Lunges",
            category = ExerciseCategory.LOWER_BODY,
            difficulty = "Intermediate",
            targetMuscles = listOf("Quadriceps", "Glutes", "Hamstrings", "Calves", "Hip Stabilizers"),
            equipment = "No Equipment",
            caloriesPerMinute = 8,
            defaultDurationSeconds = 60,
            overview = "Unilateral lower body powerhouse that fixes muscle imbalances, enhances balance, and tones legs and hips.",
            drawableResName = "img_squats",
            steps = listOf(
                ExerciseStep(1, "Standing Tall", "Stand with feet hip-width apart, hands on hips or clasped at chest.", "Brace core and find a focal point in front of you.", "lunge_step_1"),
                ExerciseStep(2, "Step Forward", "Take a big step forward (about 2-3 feet) with your right foot.", "Keep torso perpendicular to the floor.", "lunge_step_2"),
                ExerciseStep(3, "Lower Both Knees", "Lower hips until both front and back knees bend to approximately 90 degrees.", "Back knee hovers 1-2 inches above the ground.", "lunge_step_3"),
                ExerciseStep(4, "Push Back to Start", "Press through front heel to return to standing, then alternate to left leg.", "Keep movements smooth, controlled, and stable.", "lunge_step_4")
            ),
            correctPostureTips = listOf(
                "Front knee stays stacked over front ankle, not past toes.",
                "Torso remains tall and upright, not tilted forward."
            ),
            safetyTips = listOf(
                "Step backward into reverse lunges if forward lunges irritate kneecaps."
            ),
            commonMistakes = listOf(
                "Banging back knee hard into the floor.",
                "Narrow stepping stance (walking a tightrope) causing loss of balance."
            )
        ),
        Exercise(
            id = "calf_raises",
            title = "Calf Raises",
            category = ExerciseCategory.LOWER_BODY,
            difficulty = "Beginner",
            targetMuscles = listOf("Gastrocnemius", "Soleus", "Ankle Stabilizers"),
            equipment = "Wall or Step (Optional)",
            caloriesPerMinute = 5,
            defaultDurationSeconds = 60,
            overview = "Strengthens calf muscles, protects Achilles tendons, and enhances running and jumping ankle spring.",
            drawableResName = "img_squats",
            steps = listOf(
                ExerciseStep(1, "Starting Stance", "Stand straight with feet hip-width apart, toes pointing forward.", "Lightly touch a wall for balance if needed.", "calf_step_1"),
                ExerciseStep(2, "Ascend on Toes", "Exhale and press down through the balls of both feet to raise heels as high as possible.", "Squeeze calves hard at the top peak.", "calf_step_2"),
                ExerciseStep(3, "Peak Hold", "Pause at the very top for 1 full second with glutes and core engaged.", "Distribute pressure evenly across big and pinky toes.", "calf_step_3"),
                ExerciseStep(4, "Controlled Descent", "Inhale and lower heels back down slowly over 2 seconds until lightly touching floor.", "Do not bounce at the bottom.", "calf_step_4")
            ),
            correctPostureTips = listOf(
                "Keep legs straight with knees soft, never locked rigid.",
                "Body travels straight up and down, not leaning forward."
            ),
            safetyTips = listOf(
                "Perform on flat ground first before attempting on the edge of a step."
            ),
            commonMistakes = listOf(
                "Bouncing up and down using Achilles tendon elasticity instead of muscular contraction.",
                "Rolling outward onto outer edges of feet."
            )
        ),
        Exercise(
            id = "glute_bridges",
            title = "Glute Bridges",
            category = ExerciseCategory.LOWER_BODY,
            difficulty = "Beginner",
            targetMuscles = listOf("Gluteus Maximus", "Hamstrings", "Core", "Lower Back"),
            equipment = "Exercise Mat",
            caloriesPerMinute = 6,
            defaultDurationSeconds = 60,
            overview = "Wakes up dormant glutes, stabilizes the sacroiliac joint, and counteracts prolonged sitting tightness.",
            drawableResName = "img_squats",
            steps = listOf(
                ExerciseStep(1, "Supine Setup", "Lie on back with knees bent, feet flat on floor hip-width apart, arms resting by sides.", "Fingertips should just graze heels.", "bridge_step_1"),
                ExerciseStep(2, "Pelvic Brace", "Flatten lower back into mat by rotating pelvis backward and bracing abs.", "Inhale and prepare.", "bridge_step_2"),
                ExerciseStep(3, "Drive Hips Up", "Exhale, drive through heels and lift hips until knees, hips, and shoulders form a diagonal line.", "Squeeze glutes hard at the top.", "bridge_step_3"),
                ExerciseStep(4, "Hold & Lower", "Hold top squeeze for 2 seconds, then lower hips with control back to floor.", "Repeat for repetitions.", "bridge_step_4")
            ),
            correctPostureTips = listOf(
                "Do not over-arch lumbar spine at the top.",
                "Keep knees parallel—do not let them splay wide or collapse in."
            ),
            safetyTips = listOf(
                "Push through heels, not toes, to maximize glute contraction."
            ),
            commonMistakes = listOf(
                "Hyperextending lower back to get hips higher.",
                "Using hamstrings only by placing feet too far away."
            )
        ),

        // ==================== CORE EXERCISES ====================
        Exercise(
            id = "plank",
            title = "Plank",
            category = ExerciseCategory.CORE,
            difficulty = "Beginner",
            targetMuscles = listOf("Transverse Abdominis", "Rectus Abdominis", "Obliques", "Shoulders"),
            equipment = "Exercise Mat",
            caloriesPerMinute = 7,
            defaultDurationSeconds = 45,
            overview = "The gold standard isometric core stability exercise that builds rock-solid abdominal endurance and protects the spine.",
            drawableResName = "img_plank",
            steps = listOf(
                ExerciseStep(1, "Forearm Setup", "Place forearms on mat with elbows directly under shoulders, forearms parallel.", "Clasp hands or keep palms flat on mat.", "plank_step_1"),
                ExerciseStep(2, "Extend Legs", "Step feet back hip-width apart, resting on the balls of your feet.", "Form a rigid straight line from crown of head to heels.", "plank_step_2"),
                ExerciseStep(3, "Full Body Tension", "Squeeze glutes, pull kneecaps up, and draw belly button in toward spine.", "Push forearms into mat so upper back doesn't sink.", "plank_step_3"),
                ExerciseStep(4, "Breathe & Hold", "Maintain steady, rhythmic breathing through your diaphragm while holding position.", "Do not let hips sag or hike into a tent.", "plank_step_4")
            ),
            correctPostureTips = listOf(
                "Gaze down at mat 4 inches in front of hands to keep cervical spine neutral.",
                "Glutes clamped tight to prevent lower back sway.",
                "Shoulder blades spread across rib cage (active protraction)."
            ),
            safetyTips = listOf(
                "Drop knees to mat for a knee plank if lower back begins aching.",
                "Stop immediately if you feel pain in the lumbar spine."
            ),
            commonMistakes = listOf(
                "Sagging hips, putting heavy shear force on lumbar spine.",
                "Hicking hips high in the air to make the exercise easier.",
                "Holding breath and elevating blood pressure."
            )
        ),
        Exercise(
            id = "crunches",
            title = "Crunches",
            category = ExerciseCategory.CORE,
            difficulty = "Beginner",
            targetMuscles = listOf("Upper Rectus Abdominis", "Transverse Abdominis"),
            equipment = "Exercise Mat",
            caloriesPerMinute = 6,
            defaultDurationSeconds = 60,
            overview = "Isolates the abdominal wall with controlled spinal flexion, defining upper ab muscles safely.",
            drawableResName = "img_plank",
            steps = listOf(
                ExerciseStep(1, "Mat Setup", "Lie on back with knees bent, feet flat on floor hip-width apart.", "Place fingertips lightly behind ears or cross arms over chest.", "crunch_step_1"),
                ExerciseStep(2, "Engage Abdominals", "Draw navel to spine, pressing lower back flat against floor.", "Inhale to prepare.", "crunch_step_2"),
                ExerciseStep(3, "Curl Upward", "Exhale and curl shoulders and upper back off floor 3-4 inches using ab muscles.", "Imagine bringing ribs closer toward hip bones.", "crunch_step_3"),
                ExerciseStep(4, "Lower with Control", "Inhale and lower shoulders back down with control without relaxing abdominal tension.", "Keep chin slightly tucked, avoiding neck pulling.", "crunch_step_4")
            ),
            correctPostureTips = listOf(
                "Keep space of an apple between your chin and your chest.",
                "Do not pull your head or neck forward with hands."
            ),
            safetyTips = listOf(
                "Lower back remains glued to the mat; do not perform full sit-ups if neck or back is sensitive."
            ),
            commonMistakes = listOf(
                "Yanking on the back of the neck with hands.",
                "Using momentum and swinging arms up."
            )
        ),
        Exercise(
            id = "bicycle_crunches",
            title = "Bicycle Crunches",
            category = ExerciseCategory.CORE,
            difficulty = "Intermediate",
            targetMuscles = listOf("Internal & External Obliques", "Rectus Abdominis", "Hip Flexors"),
            equipment = "Exercise Mat",
            caloriesPerMinute = 8,
            defaultDurationSeconds = 60,
            overview = "Rated by biomechanics studies as one of the most effective exercises for targeting the six-pack and waistline obliques.",
            drawableResName = "img_plank",
            steps = listOf(
                ExerciseStep(1, "Starting Tabletop", "Lie on back, hands behind head, knees lifted at 90-degree tabletop, shoulders lifted.", "Press lower back firmly into the floor.", "bike_step_1"),
                ExerciseStep(2, "Rotate Right to Left", "Extend right leg forward 45 degrees while rotating right shoulder toward left knee.", "Exhale and twist through your ribcage, not just elbow.", "bike_step_2"),
                ExerciseStep(3, "Switch Sides Smoothly", "Pedal smoothly: bend right knee in toward chest while extending left leg out 45 degrees.", "Rotate left shoulder toward right knee.", "bike_step_3"),
                ExerciseStep(4, "Controlled Cadence", "Continue alternating sides in a deliberate, slow, swimming tempo.", "Focus on rotational muscle squeeze.", "bike_step_4")
            ),
            correctPostureTips = listOf(
                "Elbows wide; lead rotation from shoulder and armpit.",
                "Extended leg hovers off floor, engaging lower abdominals."
            ),
            safetyTips = listOf(
                "Keep legs higher toward ceiling (60-70 degrees) if lower back arches."
            ),
            commonMistakes = listOf(
                "Rushing through reps with erratic flailing elbows.",
                "Pulling head with hands and compressing cervical spine."
            )
        ),

        // ==================== STRETCHING ====================
        Exercise(
            id = "neck_stretch",
            title = "Neck Stretch",
            category = ExerciseCategory.STRETCHING,
            difficulty = "Beginner",
            targetMuscles = listOf("Upper Trapezius", "Levator Scapulae", "Scalenes"),
            equipment = "No Equipment",
            caloriesPerMinute = 3,
            defaultDurationSeconds = 60,
            overview = "Releases built-up tension in the cervical spine and shoulders caused by screen time and daily stress.",
            drawableResName = "img_stretching",
            steps = listOf(
                ExerciseStep(1, "Sit or Stand Tall", "Sit upright in a chair or stand tall with shoulders dropped back and down.", "Relax jaw and take a deep breath.", "neck_step_1"),
                ExerciseStep(2, "Lateral Tilt", "Gently tilt right ear toward right shoulder until you feel a pleasant stretch in left side of neck.", "Keep opposite shoulder weighed down.", "neck_step_2"),
                ExerciseStep(3, "Gentle Hand Assist", "Rest right hand gently on top of head with zero pulling force; just natural arm weight.", "Hold for 20-30 seconds breathing deeply.", "neck_step_3"),
                ExerciseStep(4, "Switch Sides", "Slowly return to center, then tilt left ear toward left shoulder and repeat.", "Notice tension releasing with each exhale.", "neck_step_4")
            ),
            correctPostureTips = listOf(
                "Keep torso completely still; only the neck moves.",
                "Do not shrug shoulder upward to meet the ear."
            ),
            safetyTips = listOf(
                "Never yank or jerk the neck. Stretch only to mild tension, never pain."
            ),
            commonMistakes = listOf(
                "Aggressively pulling head down with hand.",
                "Hunching upper back forward while stretching."
            )
        ),
        Exercise(
            id = "shoulder_stretch",
            title = "Shoulder Stretch",
            category = ExerciseCategory.STRETCHING,
            difficulty = "Beginner",
            targetMuscles = listOf("Posterior Deltoid", "Infraspinatus", "Upper Back"),
            equipment = "No Equipment",
            caloriesPerMinute = 3,
            defaultDurationSeconds = 60,
            overview = "Cross-body arm stretch that opens the posterior shoulder capsule, improves arm reach, and reduces upper back tightness.",
            drawableResName = "img_stretching",
            steps = listOf(
                ExerciseStep(1, "Cross Right Arm", "Stand tall. Bring right arm straight across your chest at shoulder height.", "Keep right arm elongated with palm facing in.", "shoulder_step_1"),
                ExerciseStep(2, "Support with Left Arm", "Hook left forearm or hand underneath right triceps/elbow area.", "Gently draw right arm closer into your chest.", "shoulder_step_2"),
                ExerciseStep(3, "Lower Shoulder Blade", "Slide right shoulder blade down away from your ear to maximize the deltoid stretch.", "Hold for 20-30 seconds while breathing slowly.", "shoulder_step_3"),
                ExerciseStep(4, "Release & Alternate", "Slowly release right arm, shake shoulders out, and repeat on left side.", "Maintain balanced breath on both sides.", "shoulder_step_4")
            ),
            correctPostureTips = listOf(
                "Keep hips and torso squared forward; avoid twisting body.",
                "Keep stretching arm horizontal at chest height."
            ),
            safetyTips = listOf(
                "Press on the fleshy part of the arm, never directly against the elbow joint."
            ),
            commonMistakes = listOf(
                "Shoulder rising up toward ear, cutting off the posterior deltoid stretch.",
                "Twisting whole torso to the side."
            )
        ),
        Exercise(
            id = "hamstring_stretch",
            title = "Hamstring Stretch",
            category = ExerciseCategory.STRETCHING,
            difficulty = "Beginner",
            targetMuscles = listOf("Biceps Femoris", "Semitendinosus", "Calves", "Lower Back"),
            equipment = "No Equipment",
            caloriesPerMinute = 3,
            defaultDurationSeconds = 60,
            overview = "Lengthens tight hamstring tendons along the back of thighs, relieving lower back tension and improving gait flexibility.",
            drawableResName = "img_stretching",
            steps = listOf(
                ExerciseStep(1, "Staggered Stance", "Stand tall and place right heel on floor about 12 inches ahead, flexing toes up toward shin.", "Keep left knee slightly bent.", "hamstring_step_1"),
                ExerciseStep(2, "Hip Hinge", "Place hands on left thigh. Hinge forward at your hips, sending tailbone backward.", "Keep spine long and chest open.", "hamstring_step_2"),
                ExerciseStep(3, "Feel the Stretch", "Lower chest toward right thigh until a comfortable stretch is felt along back of right leg.", "Hold for 25-30 seconds, taking deep diaphragm breaths.", "hamstring_step_3"),
                ExerciseStep(4, "Stand & Switch", "Press through feet to stand tall, then extend left heel forward and repeat.", "Sink slightly deeper on each exhale.", "hamstring_step_4")
            ),
            correctPostureTips = listOf(
                "Keep spine straight and flat; do not round shoulders into a hunch.",
                "Toes pointed directly up toward the ceiling."
            ),
            safetyTips = listOf(
                "Do not lock front knee joint backwards (avoid hyperextension)."
            ),
            commonMistakes = listOf(
                "Rounding the thoracic and lumbar spine to reach toes with hands.",
                "Resting hands on the front stretching knee joint."
            )
        ),
        Exercise(
            id = "standing_side_stretch",
            title = "Standing Side Stretch",
            category = ExerciseCategory.STRETCHING,
            difficulty = "Beginner",
            targetMuscles = listOf("Latissimus Dorsi", "Obliques", "Intercostals", "Quadratus Lumborum"),
            equipment = "No Equipment",
            caloriesPerMinute = 3,
            defaultDurationSeconds = 60,
            overview = "Expands the ribcage, stretches the lateral torso and spinal rotators, and promotes deeper lung capacity.",
            drawableResName = "img_stretching",
            steps = listOf(
                ExerciseStep(1, "Mountain Pose Setup", "Stand with feet hip-width apart, weight balanced evenly between both feet.", "Engage thighs and lengthen spine.", "side_step_1"),
                ExerciseStep(2, "Reach Overhead", "Inhale and raise right arm straight overhead, resting left hand on left outer hip/thigh.", "Reach right fingertips tall toward ceiling.", "side_step_2"),
                ExerciseStep(3, "Lateral Bend", "Exhale and gently curve torso over to the left side in an elegant crescent arc.", "Feel whole right side of body opening from hip to fingers.", "side_step_3"),
                ExerciseStep(4, "Hold & Reverse", "Hold for 20 seconds, inhale back to center, lower arm, and repeat on left side.", "Keep chest facing forward without twisting down.", "side_step_4")
            ),
            correctPostureTips = listOf(
                "Keep both feet rooted evenly into the floor without shifting all weight to one side.",
                "Keep chest rotated open to front, not turned toward the floor."
            ),
            safetyTips = listOf(
                "Avoid crunching into the compression side; visualize lifting up before bending over."
            ),
            commonMistakes = listOf(
                "Leaning forward or twisting the upper body toward the floor.",
                "Allowing the opposite hip to push out erratically."
            )
        )
    )

    fun getExerciseById(id: String): Exercise? {
        return allExercises.find { it.id.equals(id, ignoreCase = true) }
    }

    fun getExercisesByCategory(category: ExerciseCategory): List<Exercise> {
        return allExercises.filter { it.category == category }
    }
}
