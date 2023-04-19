<if test="queryCondition.idSort != null">
                <if test="queryCondition.idSort">id asc</if> <if test="!queryCondition.idSort">id desc</if>
            </if>
            <if test="queryCondition.idSort != null">
                <if test="queryCondition.idSort">, room_area asc</if> <if test="!queryCondition.idSort">, room_area desc</if>
            </if>
            <if test="queryCondition.idSort != null">
                <if test="queryCondition.idSort">, room_type_capacity asc</if> <if test="!queryCondition.idSort">, room_type_capacity desc</if>
            </if>



    <choose>
            <when test="queryCondition.idSort != null">
                #{queryCondition.idSort}
            </when>
            <when test="queryCondition.areaSort != null">
                #{queryCondition.areaSort}
            </when>
            <when test="queryCondition.capacitySort != null">
                #{queryCondition.capacitySort}
            </when>
        </choose>
