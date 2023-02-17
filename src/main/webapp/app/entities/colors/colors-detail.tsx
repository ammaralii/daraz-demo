import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './colors.reducer';

export const ColorsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const colorsEntity = useAppSelector(state => state.colors.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="colorsDetailsHeading">Colors</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{colorsEntity.id}</dd>
          <dt>
            <span id="coloruid">Coloruid</span>
          </dt>
          <dd>{colorsEntity.coloruid}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{colorsEntity.name}</dd>
          <dt>Car</dt>
          <dd>
            {colorsEntity.cars
              ? colorsEntity.cars.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {colorsEntity.cars && i === colorsEntity.cars.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/colors" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/colors/${colorsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ColorsDetail;
