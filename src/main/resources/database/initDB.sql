CREATE TABLE IF NOT EXISTS divisions
(
    id              BIGSERIAL       PRIMARY KEY,
    name            varchar(150)    NOT NULL,
    parent_id       bigint          REFERENCES divisions,
    dt_from         timestamp       NOT NULL,
    dt_till         timestamp,
    sort_priority   integer         DEFAULT 0,
    is_system       integer         DEFAULT 0,
    creation_date   timestamp       NOT NULL,
    correction_date timestamp,
    tech_comment    varchar(1024)
);

CREATE INDEX ON divisions (creation_date);

COMMENT ON TABLE divisions IS 'Справочник подразделений';

COMMENT ON COLUMN divisions.name            IS 'Наименование подразделения';
COMMENT ON COLUMN divisions.parent_id       IS 'Ссылка на родительское подразделение';
COMMENT ON COLUMN divisions.dt_from         IS 'Дата-время начала действия';
COMMENT ON COLUMN divisions.dt_till         IS 'Дата-время завершения действия. Null = бессрочно';
COMMENT ON COLUMN divisions.sort_priority   IS 'Приоритет для сортировки при отображении';
COMMENT ON COLUMN divisions.is_system       IS 'Признак Системный (только для подразд. верхнего уровня)';
COMMENT ON COLUMN divisions.creation_date   IS 'Дата создания';
COMMENT ON COLUMN divisions.correction_date IS 'Дата последнего редактирования';
COMMENT ON COLUMN divisions.tech_comment    IS 'Комментарий ТП либо того кто изменил запись руками по какой либо причине';